package com.yizhenwind.booster.order.ui.subject.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.booster.common.constant.Constant
import com.yizhenwind.booster.common.constant.IntentKey
import com.yizhenwind.booster.component.base.IActivityLaunchArgDeserializer
import com.yizhenwind.booster.component.base.IActivityLaunchArgs

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/4
 */
data class CreateSubjectLaunchArgs(
    val categoryId: Long = Constant.DEFAULT_ID
) : IActivityLaunchArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateSubjectActivity::class.java).apply {
            putExtra(IntentKey.CATEGORY_ID, categoryId)
        }

    companion object : IActivityLaunchArgDeserializer<CreateSubjectLaunchArgs> {

        override fun deserialize(intent: Intent): CreateSubjectLaunchArgs =
            CreateSubjectLaunchArgs(intent.getLongExtra(IntentKey.CATEGORY_ID, Constant.DEFAULT_ID))

    }
}
