package com.yizhenwind.booster.customer.ui.tab.detail

import com.yizhenwind.booster.customer.databinding.FragmentCustomerDetailBinding
import com.yizhenwind.booster.framework.base.BaseFragment
import com.yizhenwind.booster.framework.ext.formatToDateTime
import com.yizhenwind.booster.framework.ext.fragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/20
 */
class CustomerDetailFragment :
    BaseFragment<FragmentCustomerDetailBinding>(FragmentCustomerDetailBinding::inflate) {

    private val args by fragmentArgs<CustomerDetailArgs>()

    override fun initPage() {
        binding.apply {
            args.customer.apply {
                hlvCustomerDetailName.content = name
                hlvCustomerDetailContactType.content = contactType.value
                hlvCustomerDetailContact.content = contact
                hlvCustomerDetailRemark.content = remark
                hlvCustomerDetailCreateTime.content = createTime.formatToDateTime()
            }
        }
    }
}