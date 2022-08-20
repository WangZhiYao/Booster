package com.yizhenwind.booster.character.ui.info.order

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.BasePagingDataFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@AndroidEntryPoint
class CharacterOrderFragment :
    BasePagingDataFragment<Order, CharacterOrderAdapter, CharacterOrderViewHolder>() {

    private val viewModel by viewModels<CharacterOrderViewModel>()

    private val args by fragmentArgs(CharacterOrderArgs::deserialize)

    override fun init() {
        super.init()
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
        viewModel.observeOrderListByCharacterId(args.characterId)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(requireContext())

    override fun getListAdapter(): CharacterOrderAdapter = CharacterOrderAdapter()

    private suspend fun render(state: CharacterOrderViewState) {
        when (state) {
            is CharacterOrderViewState.GetCharacterOrderListSuccess ->
                adapter.submitData(state.characterOrderList)
        }
    }

    private fun handleSideEffect(sideEffect: CharacterOrderSideEffect) {

    }
}