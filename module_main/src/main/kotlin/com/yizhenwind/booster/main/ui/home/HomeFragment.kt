package com.yizhenwind.booster.main.ui.home

import androidx.fragment.app.viewModels
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

}