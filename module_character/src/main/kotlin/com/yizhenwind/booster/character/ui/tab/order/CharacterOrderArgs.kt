package com.yizhenwind.booster.character.ui.tab.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
data class CharacterOrderArgs(
    val characterId: Long
) : IFragmentArgs {

    override fun newInstance(): Fragment = CharacterOrderFragment().apply {
        arguments = Bundle().apply {
            putLong(IntentKey.CHARACTER_ID, characterId)
        }
    }

    companion object : IFragmentArgDeserializer<CharacterOrderArgs> {

        @JvmStatic
        override fun deserialize(arguments: Bundle): CharacterOrderArgs = arguments.run {
            CharacterOrderArgs(getLong(IntentKey.CHARACTER_ID, Constant.DEFAULT_ID))
        }
    }
}