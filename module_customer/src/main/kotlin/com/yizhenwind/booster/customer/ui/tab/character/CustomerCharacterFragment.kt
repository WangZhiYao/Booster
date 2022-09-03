package com.yizhenwind.booster.customer.ui.tab.character

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.component.base.BaseListMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
    BaseListMVIFragment<CustomerCharacterViewState, CustomerCharacterSideEffect>() {

    private val viewModel by viewModels<CustomerCharacterViewModel>()
    private val args by fragmentArgs<CustomerCharacterArgs>()
    private val adapter: CustomerCharacterAdapter = CustomerCharacterAdapter()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    override fun initView() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CustomerCharacterFragment.adapter
        }

        adapter.onCharacterClickListener = { character ->
            characterService.launchCharacterTab(requireContext(), character)
        }

        adapter.onCreateOrderClickListener = { character ->
            orderService.launchCreateOrder(requireContext(), character.customerId, character.id)
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeCharactersByCustomerId(args.customerId)
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
}