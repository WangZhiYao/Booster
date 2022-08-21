package com.yizhenwind.booster.component.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yizhenwind.booster.component.databinding.ActivityBaseMviBinding
import com.yizhenwind.booster.component.ext.viewBinding
import org.orbitmvi.orbit.viewmodel.observe

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
abstract class BaseMVIActivity<out VB : ViewBinding, STATE : IViewState, SIDE_EFFECT : ISideEffect>(
    bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private val rootBinding by viewBinding(ActivityBaseMviBinding::inflate)

    protected val binding: VB by viewBinding(bindingInflater)
    protected abstract val viewModel: BaseMVIViewModel<STATE, SIDE_EFFECT>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootBinding.root)
        initPage()
    }

    private fun initPage() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
        rootBinding.apply {
            container.addView(binding.root)
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    abstract fun render(state: STATE)

    abstract fun handleSideEffect(sideEffect: SIDE_EFFECT)
}