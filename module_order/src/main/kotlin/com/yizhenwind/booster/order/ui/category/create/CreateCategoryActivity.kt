package com.yizhenwind.booster.order.ui.category.create

import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.common.ext.blankThenNull
import com.yizhenwind.booster.component.base.BaseTextInputActivity
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.component.ext.showSnackWithAction
import com.yizhenwind.booster.order.R
import com.yizhenwind.booster.order.databinding.ActivityCreateCategoryBinding
import com.yizhenwind.booster.order.ui.subject.create.CreateSubjectLaunchArgs

/**
 * 创建分类
 *
 * @author WangZhiYao
 * @since 2022/7/18
 */
@AndroidEntryPoint
class CreateCategoryActivity :
    BaseTextInputActivity<ActivityCreateCategoryBinding>(ActivityCreateCategoryBinding::inflate) {

    private val viewModel by viewModels<CreateCategoryViewModel>()

    override fun showBack() = true

    override fun init() {
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            tietCreateCategoryTitle.doAfterTextChanged {
                viewModel.onTitleChanged(it?.toString())
            }

            fabCreateCategorySubmit.setIntervalClickListener { attemptCreateCategory() }
        }
    }

    private fun initData() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
    }

    private fun render(state: CreateCategoryViewState) {
        binding.apply {
            when (state) {
                CreateCategoryViewState.Init -> {
                    tietCreateCategoryTitle.text = null
                    tietCreateCategoryDescription.text = null
                }
                is CreateCategoryViewState.CreateCategorySuccess -> {
                    root.showSnackWithAction(
                        R.string.create_category_success,
                        R.string.create_category_success_to_create_subject
                    ) {
                        CreateSubjectLaunchArgs(state.category.id).launch(this@CreateCategoryActivity)
                    }
                }
            }
        }
    }

    private fun handleSideEffect(sideEffect: CreateCategorySideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateCategorySideEffect.ShowTitleError -> {
                    showErrorInfo(
                        tilCreateCategoryTitle,
                        sideEffect.errorMessage,
                        tietCreateCategoryTitle
                    )
                }
                CreateCategorySideEffect.HideTitleError -> tilCreateCategoryTitle.error = null
                is CreateCategorySideEffect.CreateCategoryFailure -> root.showSnack(sideEffect.errorMessage)
            }
        }
    }

    private fun attemptCreateCategory() {
        binding.apply {
            val title = tietCreateCategoryTitle.text?.toString()
            val description = tietCreateCategoryDescription.text?.toString()
            viewModel.createCategory(title, description.blankThenNull())
        }
    }
}