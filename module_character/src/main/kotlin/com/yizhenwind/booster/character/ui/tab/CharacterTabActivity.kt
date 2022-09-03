package com.yizhenwind.booster.character.ui.tab

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.ui.tab.detail.CharacterDetailArgs
import com.yizhenwind.booster.character.ui.tab.order.CharacterOrderArgs
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.BaseTabMVIActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
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
class CharacterTabActivity : BaseTabMVIActivity<CharacterTabViewState, CharacterTabSideEffect>() {

    private val viewModel by viewModels<CharacterTabViewModel>()

    @Inject
    lateinit var orderService: IOrderService

    private val args by activityArgs<CharacterTabArgs>()

    override fun initPage() {
        super.initPage()
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    fab.apply {
                        when (position) {
                            INDEX_DETAIL -> setImageResource(R.drawable.ic_round_edit_white_24dp)
                            INDEX_ORDER -> setImageResource(R.drawable.ic_round_add_white_24dp)
                        }
                    }
                }
            })
            fab.setIntervalClickListener {
                when (viewPager.currentItem) {
                    INDEX_DETAIL -> {

                    }
                    INDEX_ORDER -> {
                        orderService.launchCreateOrder(
                            this@CharacterTabActivity,
                            args.character.customerId,
                            args.character.id
                        )
                    }
                }
            }
        }
    }

    private fun initData() {
        viewModel.observe(this, sideEffect = ::handleSideEffect)
    }

    override fun getPageTitle(): String = args.character.name

    override fun getTabTitleList(): List<Int> =
        listOf(R.string.character_info_detail, R.string.character_info_order)

    override fun getFragmentList(): List<Fragment> = args.run {
        listOf(
            CharacterDetailArgs(character).newInstance(),
            CharacterOrderArgs(character.id).newInstance()
        )
    }

    override fun handleSideEffect(sideEffect: CharacterTabSideEffect) {
        when (sideEffect) {
            CharacterTabSideEffect.DeleteCharacterSuccess -> finish()
            is CharacterTabSideEffect.DeleteCharacterFailure ->
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
            .setTitle(getString(R.string.dialog_character_info_title))
            .setMessage(getString(R.string.dialog_character_info_message_delete, character.name))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCharacter(character) }
            .show(supportFragmentManager)
    }

    companion object {

        private const val INDEX_DETAIL = 0

        private const val INDEX_ORDER = 1

    }
}