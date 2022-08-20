package com.yizhenwind.booster.character.ui.detail

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.databinding.ActivityCharacterDetailBinding
import com.yizhenwind.booster.character.ui.detail.info.CharacterInfoArgs
import com.yizhenwind.booster.character.ui.detail.order.CharacterOrderArgs
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.BaseActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.setupWithTab
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.widget.BoosterDialog
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 角色详情
 *
 * <a>https://developer.android.com/training/sign-in/biometric-auth</a>
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@AndroidEntryPoint
class CharacterDetailActivity :
    BaseActivity<ActivityCharacterDetailBinding>(ActivityCharacterDetailBinding::inflate) {

    private val viewModel by viewModels<CharacterDetailViewModel>()

    @Inject
    lateinit var orderService: IOrderService

    private val args by activityArgs(CharacterDetailLaunchArgs.Companion::deserialize)

    override fun showBack() = true

    override fun init() {
        initData()
        initView()
    }

    override fun setupHomeButton() {
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_round_arrow_white_24dp)
            setSupportActionBar(toolbar)
        }
        super.setupHomeButton()
    }

    private fun initData() {
        viewModel.observe(this, sideEffect = ::handleSideEffect)
    }

    private fun initView() {
        args.character.let {
            binding.apply {
                collapsingToolbarLayout.title = it.name
                vpCharacterDetail.apply {
                    setupWithTab(
                        this@CharacterDetailActivity,
                        tlCharacterDetail,
                        listOf(R.string.character_detail_info, R.string.character_detail_order),
                        listOf(
                            CharacterInfoArgs(it.id).newInstance(),
                            CharacterOrderArgs(it.id).newInstance()
                        )
                    )
                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            fab.apply {
                                when (position) {
                                    INDEX_INFO -> setImageResource(R.drawable.ic_round_edit_white_24dp)
                                    INDEX_ORDER -> setImageResource(R.drawable.ic_round_add_white_24dp)
                                }
                            }
                        }
                    })
                }
                fab.setIntervalClickListener { _ ->
                    when (vpCharacterDetail.currentItem) {
                        INDEX_INFO -> {

                        }
                        INDEX_ORDER -> {
                            orderService.launchCreateOrder(
                                this@CharacterDetailActivity,
                                it.customerId,
                                it.id
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSideEffect(sideEffect: CharacterDetailSideEffect) {
        when (sideEffect) {
            CharacterDetailSideEffect.DeleteCharacterSuccess -> finish()
            is CharacterDetailSideEffect.DeleteCharacterFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_character_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            attemptDeleteCharacter(args.character)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun attemptDeleteCharacter(character: Character) {
        BoosterDialog.Builder()
            .setTitle(getString(R.string.dialog_character_detail_title))
            .setMessage(getString(R.string.dialog_character_detail_message_delete, character.name))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCharacter(character) }
            .show(supportFragmentManager)
    }

    companion object {

        private const val INDEX_INFO = 0

        private const val INDEX_ORDER = 1

        fun start(context: Context, character: Character) {
            context.startActivity(
                Intent(context, CharacterDetailActivity::class.java).apply {
                    putExtra(IntentKey.CHARACTER, character)
                }
            )
        }
    }
}