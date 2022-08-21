package com.yizhenwind.booster.character.ui.info.order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Order
import com.yizhenwind.booster.component.base.BasePagingDataMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@AndroidEntryPoint
class CharacterOrderFragment :
    BasePagingDataMVIFragment<Order, CharacterOrderAdapter, CharacterOrderViewHolder, CharacterOrderViewState, CharacterOrderSideEffect>() {

    override val viewModel by viewModels<CharacterOrderViewModel>()
    override val adapter: CharacterOrderAdapter = CharacterOrderAdapter()

    private val args by fragmentArgs(CharacterOrderArgs::deserialize)

    override fun initPage() {
        super.initPage()
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

    override fun handleSideEffect(sideEffect: CharacterOrderSideEffect) {

    }
}