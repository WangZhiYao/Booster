package com.yizhenwind.booster.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Fragment 基类
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
abstract class BaseFragment<out VB : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = this.inflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
    }

    protected open fun initPage() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}