package com.yizhenwind.booster.character.ui.tab.order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.component.base.BasePagingDataMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@AndroidEntryPoint
class CharacterOrderFragment :
    BasePagingDataMVIFragment<CharacterOrderViewState, CharacterOrderSideEffect>() {

    private val viewModel by viewModels<CharacterOrderViewModel>()
    private val args by fragmentArgs(CharacterOrderArgs::deserialize)
    private val adapter = CharacterOrderAdapter()

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CharacterOrderFragment.adapter
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeOrderListByCharacterId(args.characterId)
    }

    override fun render(state: CharacterOrderViewState) {
        when (state) {
            is CharacterOrderViewState.GetCharacterOrderListSuccess ->
                lifecycleScope.launch {
                    adapter.submitData(state.characterOrderList)
                }
        }
    }
}