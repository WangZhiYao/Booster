package com.yizhenwind.booster.component.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.component.databinding.ActivityBasePagingMviBinding
import com.yizhenwind.booster.component.ext.viewBinding
import org.orbitmvi.orbit.viewmodel.observe

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
abstract class BasePagingDataMVIActivity<T : Any, VH : BaseViewHolder<T>, STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    AppCompatActivity() {

    protected val binding by viewBinding(ActivityBasePagingMviBinding::inflate)
    protected abstract val viewModel: BaseMVIViewModel<STATE, SIDE_EFFECT>
    protected abstract val layoutManager: RecyclerView.LayoutManager
    protected abstract val adapter: PagingDataAdapter<T, VH>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initPage()
    }

    private fun initPage() {
        viewModel.observe(this, state = ::render, sideEffect = ::handleSideEffect)
        binding.apply {
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
            rvList.apply {
                layoutManager = this@BasePagingDataMVIActivity.layoutManager
                adapter = this@BasePagingDataMVIActivity.adapter
            }
        }
    }

    abstract fun render(state: STATE)

    abstract fun handleSideEffect(sideEffect: SIDE_EFFECT)
}