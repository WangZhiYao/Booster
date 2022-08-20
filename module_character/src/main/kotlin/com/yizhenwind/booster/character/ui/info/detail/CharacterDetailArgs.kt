package com.yizhenwind.booster.character.ui.info.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.IFragmentArgDeserializer
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
data class CharacterDetailArgs(
    val character: Character
) : IFragmentArgs {

    override fun newInstance(): Fragment = CharacterDetailFragment().apply {
        arguments = Bundle().apply {
            putParcelable(IntentKey.CHARACTER, character)
        }
    }

    companion object : IFragmentArgDeserializer<CharacterDetailArgs> {

        override fun deserialize(arguments: Bundle): CharacterDetailArgs = arguments.run {
            CharacterDetailArgs(requireNotNull(getParcelable(IntentKey.CHARACTER)) { IntentKey.CHARACTER })
        }
    }
}