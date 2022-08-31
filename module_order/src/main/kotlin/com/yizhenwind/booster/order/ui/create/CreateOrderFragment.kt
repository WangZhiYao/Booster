package com.yizhenwind.booster.order.ui.create

import androidx.fragment.app.viewModels
import com.yizhenwind.booster.common.constant.BillingCycle
import com.yizhenwind.booster.common.model.CustomerCharacterList
import com.yizhenwind.booster.common.model.Subject
import com.yizhenwind.booster.component.base.BaseMVIFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import com.yizhenwind.booster.order.databinding.FragmentCreateOrderBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/31
 */
@AndroidEntryPoint
class CreateOrderFragment :
    BaseMVIFragment<FragmentCreateOrderBinding, CreateOrderViewState, CreateOrderSideEffect, CreateOrderViewModel>(
        FragmentCreateOrderBinding::inflate
    ) {

    override val viewModel: CreateOrderViewModel by viewModels()
    private val args by fragmentArgs(CreateOrderArgs::deserialize)

    private lateinit var customerAdapter: CustomerAdapter
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var subjectAdapter: SubjectAdapter
    private lateinit var billingCycleAdapter: BillingCycleAdapter

    override fun initPage() {
        super.initPage()
        initView()
    }

    private fun initView() {
        binding.apply {
            actvCreateOrderCustomer.setOnItemClickListener { _, _, position, _ ->
                viewModel.onCustomerSelected(customerAdapter.getItem(position))
            }

            actvCreateOrderCharacter.setOnItemClickListener { _, _, position, _ ->
                viewModel.onCharacterSelected(characterAdapter.getItem(position))
            }

            actvCreateOrderCategory.setOnItemClickListener { _, _, position, _ ->
                //viewModel.onCategorySelected(categoryAdapter.getItem(position))
            }

            actvCreateOrderSubject.setOnItemClickListener { _, _, position, _ ->
                viewModel.onSubjectSelected(subjectAdapter.getItem(position))
            }

            actvCreateOrderBillingCycle.setOnItemClickListener { _, _, position, _ ->
                viewModel.onBillingCycleSelected(BillingCycle.values()[position])
            }
        }
    }

    override fun render(state: CreateOrderViewState) {
        when (state) {
            is CreateOrderViewState.Init -> {
                initCustomerCharacterSelector(state.customerCharacterList)
                //initCategorySubjectSelector(state.categorySubjectList)
                initBillingCycleSelector(state.billingCycleList)
            }
            is CreateOrderViewState.OnCustomerSelected ->
                onCustomerSelected(state.characterList)
            is CreateOrderViewState.OnCategorySelected ->
                onCategorySelected(state.subjectList)
        }
    }

    private fun initCustomerCharacterSelector(customerCharacterList: List<CustomerCharacterList>) {
        customerCharacterList.apply {
            customerAdapter =
                CustomerAdapter(requireContext(), ArrayList(map { it.customer }))
            val customer = map { it.customer }.firstOrNull { it.id == args.customerId }

            binding.actvCreateOrderCustomer.apply {
                setAdapter(customerAdapter)
                text = null
                customer?.let {
                    setText(it.name, false)
                    viewModel.onCustomerSelected(it)
                }
            }
        }
    }

    /*private fun initCategorySubjectSelector(categorySubjectList: List<CategorySubjectList>) {
        categorySubjectList.apply {
            categoryAdapter =
                CategoryAdapter(this@CreateOrderActivity, ArrayList(map { it.category }))
            val category = map { it.category }.firstOrNull { it.id == args.categoryId }

            binding.actvCreateOrderCategory.apply {
                setAdapter(categoryAdapter)
                text = null
                category?.let {
                    setText(it.title, false)
                    viewModel.onCategorySelected(it)
                }
            }
        }
    }*/

    private fun initBillingCycleSelector(billingCycleList: List<BillingCycle>) {
        billingCycleList.apply {
            billingCycleAdapter =
                BillingCycleAdapter(requireContext(), this)
            val billingCycle = firstOrNull()

            binding.actvCreateOrderBillingCycle.apply {
                setAdapter(billingCycleAdapter)
                text = null
                billingCycle?.let {
                    setText(it.value, false)
                    viewModel.onBillingCycleSelected(it)
                }
            }
        }
    }

    private fun onCustomerSelected(characterList: List<CustomerCharacterList.Character>) {
        characterList.apply {
            characterAdapter = CharacterAdapter(requireContext(), this)
            val character = characterList.firstOrNull { it.id == args.characterId }

            binding.actvCreateOrderCharacter.apply {
                setAdapter(characterAdapter)
                text = null
                character?.let {
                    setText(it.name, false)
                }
            }
        }
    }

    private fun onCategorySelected(subjectList: List<Subject>) {
        subjectList.apply {
            subjectAdapter = SubjectAdapter(requireContext(), this)
            val subject = firstOrNull { it.id == args.subjectId }

            binding.actvCreateOrderSubject.apply {
                setAdapter(subjectAdapter)
                text = null
                subject?.let {
                    setText(it.content, false)
                }
            }
        }
    }
}