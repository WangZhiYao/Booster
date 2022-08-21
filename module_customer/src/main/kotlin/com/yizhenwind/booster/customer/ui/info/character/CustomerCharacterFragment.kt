package com.yizhenwind.booster.customer.ui.info.character

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.BasePagingDataMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 客户角色页
 *
 * @author WangZhiYao
 * @since 2022/4/20
 */
@AndroidEntryPoint
class CustomerCharacterFragment :
    BasePagingDataMVIFragment<Character, CustomerCharacterAdapter, CustomerCharacterViewHolder, CustomerCharacterViewState, CustomerCharacterSideEffect>() {

    override val viewModel by viewModels<CustomerCharacterViewModel>()
    override val adapter: CustomerCharacterAdapter = CustomerCharacterAdapter()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    private val args by fragmentArgs(CustomerCharacterArgs::deserialize)

    override fun initPage() {
        super.initPage()
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observeCharactersByCustomerId(args.customerId)
    }

    private fun initView() {
        adapter.onCharacterClickListener = { character ->
            characterService.launchCharacterInfo(requireContext(), character)
        }

        adapter.onCreateOrderClickListener = { character ->
            orderService.launchCreateOrder(requireContext(), character.customerId, character.id)
        }
    }

    override fun render(state: CustomerCharacterViewState) {
        when (state) {
            is CustomerCharacterViewState.Init -> {
                lifecycleScope.launch {
                    adapter.submitData(state.characterList)
                }
            }
        }
    }

    override fun handleSideEffect(sideEffect: CustomerCharacterSideEffect) {

    }
}