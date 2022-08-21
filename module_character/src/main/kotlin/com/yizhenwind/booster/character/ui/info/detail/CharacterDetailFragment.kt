package com.yizhenwind.booster.character.ui.info.detail

import com.yizhenwind.booster.character.databinding.FragmentCharacterDetailBinding
import com.yizhenwind.booster.common.ext.formatToDateTime
import com.yizhenwind.booster.component.base.BaseFragment
import com.yizhenwind.booster.component.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {

    private val args by fragmentArgs(CharacterDetailArgs::deserialize)

    override fun initPage() {
        binding.apply {
            args.character.apply {
                tvCharacterDetailZone.text = zone.name
                tvCharacterDetailServer.text = server.name
                tvCharacterDetailSect.text = sect.name
                tvCharacterDetailInternal.text = internal.name
                tvCharacterDetailAccount.text = account
                tvCharacterDetailPassword.text = password
                tvCharacterDetailSecurityLock.text = securityLock
                tvCharacterDetailRemark.text = remark
                tvCharacterDetailCreateTime.text = createTime.formatToDateTime()
            }
        }
    }
}