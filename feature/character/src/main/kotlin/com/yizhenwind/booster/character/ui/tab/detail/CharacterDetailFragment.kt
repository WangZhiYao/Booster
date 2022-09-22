package com.yizhenwind.booster.character.ui.tab.detail

import com.yizhenwind.booster.character.databinding.FragmentCharacterDetailBinding
import com.yizhenwind.booster.framework.base.BaseFragment
import com.yizhenwind.booster.framework.ext.formatToDateTime
import com.yizhenwind.booster.framework.ext.fragmentArgs
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {

    private val args by fragmentArgs<CharacterDetailArgs>()

    override fun initPage() {
        binding.apply {
            args.character.apply {
                hlvCharacterDetailZone.content = zone.name
                hlvCharacterDetailServer.content = server.name
                hlvCharacterDetailSect.content = sect.name
                hlvCharacterDetailInternal.content = internal.name
                hlvCharacterDetailAccount.content = account
                hlvCharacterDetailPassword.content = password
                hlvCharacterDetailSecurityLock.content = securityLock
                hlvCharacterDetailRemark.content = remark
                hlvCharacterDetailCreateTime.content = createTime.formatToDateTime()
            }
        }
    }
}