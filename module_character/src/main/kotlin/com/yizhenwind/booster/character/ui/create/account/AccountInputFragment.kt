package com.yizhenwind.booster.character.ui.create.account

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.databinding.FragmentAccountInputBinding
import com.yizhenwind.booster.character.ui.create.CreateCharacterViewModel2
import com.yizhenwind.booster.common.Account
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.component.ext.setIntervalClickListener
import com.yizhenwind.booster.component.ext.showSnack

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/10
 */
class AccountInputFragment :
    BaseFragment<FragmentAccountInputBinding>(FragmentAccountInputBinding::inflate) {

    private val activityViewModel by activityViewModels<CreateCharacterViewModel2>()

    override fun initPage() {
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            tietCreateCharacterAccount.doAfterTextChanged { account ->
                checkIfShowAccountError(account?.toString())
            }
            fabNext.setIntervalClickListener {
                tietCreateCharacterAccount.text.let { account ->
                    if (!checkIfAccountValid(account?.toString())) {
                        root.showSnack(R.string.error_account_format)
                        return@setIntervalClickListener
                    }
                    account?.let { activityViewModel.setAccount(Account(it.toString())) }
                    findNavController().navigate(R.id.action_nav_account_input_to_nav_password_input)
                }
            }
        }
    }

    private fun initData() {
        activityViewModel.account.observe(viewLifecycleOwner) { account ->
            binding.tietCreateCharacterAccount.setText(account.value)
        }
    }

    private fun checkIfShowAccountError(account: String?) {
        binding.apply {
            if (!account.isNullOrEmpty()) {
                if (!checkIfAccountValid(account)) {
                    tilCreateCharacterAccount.error = getString(R.string.error_account_format)
                    return
                }
            }

            tilCreateCharacterAccount.error = null
        }
    }

    private fun checkIfAccountValid(account: String?) =
        account?.run {
            matches(Constant.Regex.CUSTOM_ACCOUNT)
                    || matches(Constant.Regex.EMAIL)
                    || matches(Constant.Regex.PHONE_NUMBER)
        } ?: false
}