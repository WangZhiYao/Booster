package com.yizhenwind.booster.character.ui.detail.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
data class CharacterInfoArgs(
    val characterId: Long
) : IFragmentArgs {

    override fun newInstance(): Fragment = CharacterInfoFragment().apply {
        arguments = Bundle().apply {
            putLong(IntentKey.CHARACTER_ID, characterId)
        }
    }

    companion object : IFragmentArgDeserializer<CharacterInfoArgs> {

        override fun deserialize(arguments: Bundle): CharacterInfoArgs = arguments.run {
            CharacterInfoArgs(getLong(IntentKey.CHARACTER_ID, Constant.DEFAULT_ID))
        }
    }
}