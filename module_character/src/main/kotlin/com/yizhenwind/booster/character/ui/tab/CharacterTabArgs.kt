package com.yizhenwind.booster.character.ui.tab

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.IActivityArgs
import com.yizhenwind.booster.component.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CharacterTabArgs(
    val character: Character
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CharacterTabActivity::class.java).apply {
            putExtra(IntentKey.CHARACTER, character)
        }

    companion object : IActivityArgsDeserializer<CharacterTabArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CharacterTabArgs = intent.run {
            CharacterTabArgs(requireNotNull(getParcelableExtra(IntentKey.CHARACTER)) { IntentKey.CHARACTER })
        }
    }
}
