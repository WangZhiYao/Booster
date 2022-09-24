package com.yizhenwind.booster.customer.ui.tab.detail

import androidx.fragment.app.activityViewModels
import com.yizhenwind.booster.customer.databinding.FragmentCustomerDetailBinding
import com.yizhenwind.booster.customer.ui.tab.CustomerTabViewModel
import com.yizhenwind.booster.customer.ui.tab.CustomerTabViewState
import com.yizhenwind.booster.framework.base.BaseFragment
import com.yizhenwind.booster.framework.ext.formatToDateTime
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
@AndroidEntryPoint
class CustomerDetailFragment :
    BaseFragment<FragmentCustomerDetailBinding>(FragmentCustomerDetailBinding::inflate) {

    private val activityViewModel by activityViewModels<CustomerTabViewModel>()

    override fun initPage() {
        activityViewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun render(state: CustomerTabViewState) {
        binding.apply {
            state.customer.apply {
                hlvCustomerDetailName.content = name
                hlvCustomerDetailContactType.content = contactType.value
                hlvCustomerDetailContact.content = contact
                hlvCustomerDetailRemark.content = remark
                hlvCustomerDetailCreateTime.content = createTime.formatToDateTime()
            }
        }
    }
}