package com.yizhenwind.booster.character.ui.create.password

import android.text.method.DigitsKeyListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.databinding.FragmentPasswordInputBinding
import com.yizhenwind.booster.character.ui.create.CreateCharacterViewModel2
import com.yizhenwind.booster.common.Password
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
class PasswordInputFragment :
    BaseFragment<FragmentPasswordInputBinding>(FragmentPasswordInputBinding::inflate) {

    private val activityViewModel by activityViewModels<CreateCharacterViewModel2>()

    override fun initPage() {
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            tietCreateCharacterPassword.apply {
                keyListener = PASSWORD_KEY_LISTENER
                doAfterTextChanged { password ->
                    checkIfShowPasswordError(password?.toString())
                }
            }
            fabNext.setIntervalClickListener {
                tietCreateCharacterPassword.text.let { password ->
                    if (!checkIfPasswordValid(password?.toString())) {
                        root.showSnack(R.string.error_password_format)
                        return@setIntervalClickListener
                    }
                    password?.let { activityViewModel.setPassword(Password(it.toString())) }
                }
            }
        }
    }

    private fun initData() {
        activityViewModel.password.observe(viewLifecycleOwner) { password ->
            binding.tietCreateCharacterPassword.setText(password.value)
        }
    }

    private fun checkIfShowPasswordError(password: String?) {
        binding.apply {
            if (!password.isNullOrEmpty()) {
                if (!checkIfPasswordValid(password)) {
                    tilCreateCharacterPassword.error = getString(R.string.error_password_format)
                    return
                }
            }

            tilCreateCharacterPassword.error = null
        }
    }

    private fun checkIfPasswordValid(password: String?): Boolean {
        if (password.isNullOrEmpty()) {
            return false
        }

        return password.matches(Constant.Regex.PASSWORD)
    }

    companion object {

        private val PASSWORD_KEY_LISTENER =
            DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#\$%^&*()-_=+[{]}\\|;:'\",<.>/?")
    }

}