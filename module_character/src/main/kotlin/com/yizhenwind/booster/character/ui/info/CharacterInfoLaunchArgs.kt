package com.yizhenwind.booster.character.ui.info

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.IActivityLaunchArgDeserializer
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
data class CharacterInfoLaunchArgs(
    val character: Character
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CharacterTabActivity::class.java).apply {
            putExtra(IntentKey.CHARACTER, character)
        }

    companion object : IActivityLaunchArgDeserializer<CharacterInfoLaunchArgs> {

        override fun deserialize(intent: Intent): CharacterInfoLaunchArgs = intent.run {
            CharacterInfoLaunchArgs(
                requireNotNull(getParcelableExtra(IntentKey.CHARACTER)) { IntentKey.CHARACTER }
            )
        }
    }
}
