package com.yizhenwind.booster.character.ui.detail

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
data class CharacterDetailLaunchArgs(
    val character: Character
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CharacterDetailActivity::class.java).apply {
            putExtra(IntentKey.CHARACTER, character)
        }

    companion object : IActivityLaunchArgDeserializer<CharacterDetailLaunchArgs> {

        override fun deserialize(intent: Intent): CharacterDetailLaunchArgs = intent.run {
            CharacterDetailLaunchArgs(
                requireNotNull(getParcelableExtra(IntentKey.CHARACTER)) { IntentKey.CHARACTER }
            )
        }
    }
}
