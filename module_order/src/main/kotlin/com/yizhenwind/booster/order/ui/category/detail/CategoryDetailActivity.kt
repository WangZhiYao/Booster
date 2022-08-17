package com.yizhenwind.booster.order.ui.category.detail

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.common.model.Category
import com.yizhenwind.booster.component.base.BaseActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.widget.BoosterDialog
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.databinding.ActivityCategoryDetailBinding
import com.yizhenwind.booster.order.ui.subject.create.CreateSubjectLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/27
 */
@AndroidEntryPoint
class CategoryDetailActivity :
    BaseActivity<ActivityCategoryDetailBinding>(ActivityCategoryDetailBinding::inflate) {

    private val viewModel by viewModels<CategoryDetailViewModel>()

    private val args by activityArgs(CategoryDetailLaunchArgs::deserialize)

    private val adapter by lazy { SubjectAdapter() }

    override fun showBack() = true

    override fun init() {
        initView()
        initData()
    }

    override fun setupHomeButton() {
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_round_arrow_white_24dp)
            setSupportActionBar(toolbar)
        }
        super.setupHomeButton()
    }

    private fun initView() {
        args.category.let { category ->
            binding.apply {
                collapsingToolbarLayout.title = category.title
                rvSubject.adapter = adapter
                fabCategoryDetailAddSubject.setIntervalClickListener {
                    CreateSubjectLaunchArgs(category.id).launch(this@CategoryDetailActivity)
                }
            }
        }
    }

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
        viewModel.observeSubjectListByCategoryId(args.category.id)
    }

    private suspend fun render(state: CategoryDetailViewState) {
        when (state) {
            is CategoryDetailViewState.Init -> {
                adapter.submitData(state.subjectList)
            }
        }
    }

    private fun handleSideEffect(sideEffect: CategoryDetailSideEffect) {
        when (sideEffect) {
            CategoryDetailSideEffect.DeleteCategorySuccess -> finish()
            is CategoryDetailSideEffect.DeleteCategoryFailure ->
                binding.root.showSnack(sideEffect.errorMessage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_category_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            attemptDeleteCategory(args.category)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun attemptDeleteCategory(category: Category) {
        BoosterDialog.Builder()
            .setTitle(getString(R.string.dialog_category_detail_title))
            .setMessage(getString(R.string.dialog_category_detail_message_delete, category.title))
            .setNegativeButton(getString(R.string.cancel)) { it.dismiss() }
            .setPositiveButton(getString(R.string.ok)) { viewModel.deleteCategory(category) }
            .show(supportFragmentManager)
    }
}