package com.yizhenwind.booster.customer.ui.info.character

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.BasePagingDataFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

/**
 * 客户角色页
 *
 * @author WangZhiYao
 * @since 2022/4/20
 */
@AndroidEntryPoint
class CustomerCharacterFragment :
    BasePagingDataFragment<Character, CustomerCharacterAdapter, CustomerCharacterViewHolder>() {

    private val viewModel by viewModels<CustomerCharacterViewModel>()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    private val args by fragmentArgs(CustomerCharacterArgs::deserialize)

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeCharactersByCustomerId(args.customerId)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(requireContext())

    override fun getListAdapter(): CustomerCharacterAdapter = CustomerCharacterAdapter()

    private fun initView() {
        adapter.onCharacterClickListener = { character ->
            characterService.launchCharacterInfo(requireContext(), character)
        }

        adapter.onCreateOrderClickListener = { character ->
            orderService.launchCreateOrder(requireContext(), character.customerId, character.id)
        }
    }

    private suspend fun render(state: CustomerCharacterViewState) {
        when (state) {
            is CustomerCharacterViewState.Init -> adapter.submitData(state.characterList)
        }
    }
}