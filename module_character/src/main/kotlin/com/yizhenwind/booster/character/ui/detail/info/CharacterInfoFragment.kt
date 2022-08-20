package com.yizhenwind.booster.character.ui.detail.info

import androidx.fragment.app.viewModels
import com.yizhenwind.booster.character.databinding.FragmentCharacterInfoBinding
import com.yizhenwind.booster.common.ext.formatToDateTime
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@AndroidEntryPoint
class CharacterInfoFragment :
    BaseFragment<FragmentCharacterInfoBinding>(FragmentCharacterInfoBinding::inflate) {

    private val viewModel by viewModels<CharacterInfoViewModel>()

    private val args by fragmentArgs(CharacterInfoArgs::deserialize)

    override fun init() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
        viewModel.getCharacterById(args.characterId)
    }

    private fun render(state: CharacterInfoViewState) {
        when (state) {
            CharacterInfoViewState.Init -> {}
            is CharacterInfoViewState.GetCharacterSuccess -> {
                binding.apply {
                    state.character.apply {
                        tvCharacterInfoZone.text = zone.name
                        tvCharacterInfoServer.text = server.name
                        tvCharacterInfoSect.text = sect.name
                        tvCharacterInfoInternal.text = internal.name
                        tvCharacterInfoAccount.text = account
                        tvCharacterInfoPassword.text = password
                        tvCharacterInfoSecurityLock.text = securityLock
                        tvCharacterInfoCreateTime.text = createTime.formatToDateTime()
                    }
                }
            }
        }
    }

    private fun handleSideEffect(sideEffect: CharacterInfoSideEffect) {

    }
}