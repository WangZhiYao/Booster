package com.yizhenwind.booster.main.ui.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.main.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun init() {

    }

}