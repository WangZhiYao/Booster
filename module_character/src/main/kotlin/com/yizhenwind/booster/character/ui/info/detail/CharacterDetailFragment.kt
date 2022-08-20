package com.yizhenwind.booster.character.ui.info.detail

import androidx.fragment.app.viewModels
import com.yizhenwind.booster.character.databinding.FragmentCharacterDetailBinding
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
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {

    private val viewModel by viewModels<CharacterDetailViewModel>()

    private val args by fragmentArgs(CharacterDetailArgs::deserialize)

    override fun init() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
        viewModel.getCharacterById(args.characterId)
    }

    private fun render(state: CharacterDetailViewState) {
        when (state) {
            CharacterDetailViewState.Init -> {}
            is CharacterDetailViewState.GetCharacterSuccess -> {
                binding.apply {
                    state.character.apply {
                        tvCharacterDetailZone.text = zone.name
                        tvCharacterDetailServer.text = server.name
                        tvCharacterDetailSect.text = sect.name
                        tvCharacterDetailInternal.text = internal.name
                        tvCharacterDetailAccount.text = account
                        tvCharacterDetailPassword.text = password
                        tvCharacterDetailSecurityLock.text = securityLock
                        tvCharacterDetailCreateTime.text = createTime.formatToDateTime()
                    }
                }
            }
        }
    }

    private fun handleSideEffect(sideEffect: CharacterDetailSideEffect) {

    }
}