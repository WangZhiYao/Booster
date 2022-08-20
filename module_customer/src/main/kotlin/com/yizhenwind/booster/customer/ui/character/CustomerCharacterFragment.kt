package com.yizhenwind.booster.customer.ui.character

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import com.yizhenwind.booster.customer.databinding.FragmentCustomerCharacterBinding
import com.yizhenwind.booster.mediator.character.ICharacterService
import com.yizhenwind.booster.mediator.order.IOrderService
import javax.inject.Inject

/**
 * 客户角色页
 *
 * @author WangZhiYao
 * @since 2022/4/20
 */
@AndroidEntryPoint
class CustomerCharacterFragment :
    BaseFragment<FragmentCustomerCharacterBinding>(FragmentCustomerCharacterBinding::inflate) {

    private val viewModel by viewModels<CustomerCharacterViewModel>()

    @Inject
    lateinit var characterService: ICharacterService

    @Inject
    lateinit var orderService: IOrderService

    private val args by fragmentArgs(CustomerCharacterArgs::deserialize)

    private val adapter by lazy { CustomerCharacterAdapter() }

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.observeCharactersByCustomerId(args.customerId)
    }

    private fun initView() {
        adapter.onCharacterClickListener = { character ->
            characterService.launchCharacterInfo(requireContext(), character)
        }

        adapter.onCreateOrderClickListener = { character ->
            orderService.launchCreateOrder(requireContext(), character.customerId, character.id)
        }

        binding.rvCustomerCharacter.adapter = adapter
    }

    private suspend fun render(state: CustomerCharacterViewState) {
        when (state) {
            is CustomerCharacterViewState.Init -> adapter.submitData(state.characterList)
        }
    }
}