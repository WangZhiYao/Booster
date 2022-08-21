package com.yizhenwind.booster.order.ui.subject.create

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.yizhenwind.booster.common.ext.firstOrFirst
import com.yizhenwind.booster.component.base.BaseTextInputActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.showSnack
import com.yizhenwind.booster.order.databinding.ActivityCreateSubjectBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 * 创建项目
 *
 * @author WangZhiYao
 * @since 2022/7/18
 */
@AndroidEntryPoint
class CreateSubjectActivity :
    BaseTextInputActivity<ActivityCreateSubjectBinding, CreateSubjectViewState, CreateSubjectSideEffect>(
        ActivityCreateSubjectBinding::inflate
    ) {

    override val viewModel by viewModels<CreateSubjectViewModel>()
    private val args by activityArgs(CreateSubjectLaunchArgs::deserialize)

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            actvCreateSubjectCategory.setOnItemClickListener { _, _, position, _ ->
                viewModel.onCategorySelect(categoryAdapter.getItem(position).id)
            }

            tietCreateSubjectContent.doAfterTextChanged {
                viewModel.onContentInput(it?.toString())
            }

            fabCreateSubjectSubmit.setIntervalClickListener { attemptCreateSubject() }
        }
    }

    override fun render(state: CreateSubjectViewState) {
        when (state) {
            is CreateSubjectViewState.Init -> {
                categoryAdapter = CategoryAdapter(this, state.categoryList)
                val category = state.categoryList.firstOrFirst { it.id == args.categoryId }
                binding.actvCreateSubjectCategory.apply {
                    setAdapter(categoryAdapter)
                    text = null
                    category?.let {
                        setText(it.title, false)
                        viewModel.onCategorySelect(it.id)
                    }
                }
            }
            is CreateSubjectViewState.CreateSubjectSuccess -> viewModel.init()
        }
    }

    override fun handleSideEffect(sideEffect: CreateSubjectSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateSubjectSideEffect.ShowCategoryError ->
                    showErrorInfo(tilCreateSubjectCategory, sideEffect.errorMessage)
                CreateSubjectSideEffect.HideCategoryError ->
                    binding.tilCreateSubjectCategory.error = null
                is CreateSubjectSideEffect.ShowContentError ->
                    showErrorInfo(
                        tilCreateSubjectContent,
                        sideEffect.errorMessage,
                        tietCreateSubjectContent
                    )
                CreateSubjectSideEffect.HideContentError ->
                    binding.tilCreateSubjectContent.error = null
                is CreateSubjectSideEffect.ShowSnake ->
                    binding.root.showSnack(sideEffect.errorMessage)
            }
        }
    }

    private fun attemptCreateSubject() {
        val content = binding.tietCreateSubjectContent.text.toString()
        viewModel.createSubject(viewModel.categoryId, content)
    }
}