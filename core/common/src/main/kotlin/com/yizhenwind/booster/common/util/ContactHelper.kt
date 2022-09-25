package com.yizhenwind.booster.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.yizhenwind.booster.logger.Logger

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/25
 */
object ContactHelper {

    private const val PACKAGE_NAME_QQ = "com.tencent.mobileqq"
    private const val PACKAGE_NAME_WECHAT = "com.tencent.mm"

    private const val PLACEHOLDER_QQ = "#{QQ}"
    private const val PLACEHOLDER_PHONE = "#{PHONE}"

    private const val SCHEME_QQ = "mqqwpa://im/chat?chat_type=wpa&uin=$PLACEHOLDER_QQ"
    private const val SCHEME_WECHAT = "weixin://"
    private const val SCHEME_PHONE = "tel:$PLACEHOLDER_PHONE"

    fun attemptLaunchQQ(context: Context, qq: String): Boolean =
        if (isQQInstalled(context)) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(SCHEME_QQ.replace(PLACEHOLDER_QQ, qq))
                    )
                )
                true
            } catch (ex: Exception) {
                Logger.e(ex)
                false
            }
        } else {
            false
        }

    private fun isQQInstalled(context: Context): Boolean {
        return AndroidUtil.isPackageInstalled(context.applicationContext, PACKAGE_NAME_QQ)
    }

    fun attemptLaunchWeChat(context: Context): Boolean =
        if (isWeChatInstalled(context)) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(SCHEME_WECHAT)
                    )
                )
                true
            } catch (ex: Exception) {
                Logger.e(ex)
                false
            }
        } else {
            false
        }

    private fun isWeChatInstalled(context: Context): Boolean {
        return AndroidUtil.isPackageInstalled(context.applicationContext, PACKAGE_NAME_WECHAT)
    }

    fun attemptLaunchDial(context: Context, phoneNumber: String): Boolean =
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse(
                        SCHEME_PHONE.replace(
                            PLACEHOLDER_PHONE, phoneNumber
                        )
                    )
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            true
        } catch (ex: Exception) {
            Logger.e(ex)
            false
        }
}