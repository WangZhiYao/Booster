package com.yizhenwind.booster.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri

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

    fun attemptStartQQ(context: Context, qq: String) {
        if (isQQInstalled(context)) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(SCHEME_QQ.replace(PLACEHOLDER_QQ, qq))
                )
            )
        }
    }

    private fun isQQInstalled(context: Context): Boolean {
        return AndroidUtil.isPackageInstalled(context.applicationContext, PACKAGE_NAME_QQ)
    }

    fun attemptStartWeChat(context: Context) {
        if (isWeChatInstalled(context)) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(SCHEME_WECHAT)
                )
            )
        }
    }

    private fun isWeChatInstalled(context: Context): Boolean {
        return AndroidUtil.isPackageInstalled(context.applicationContext, PACKAGE_NAME_WECHAT)
    }

    fun attemptStartDial(context: Context, phoneNumber: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_DIAL, Uri.parse(
                    SCHEME_PHONE.replace(
                        PLACEHOLDER_PHONE, phoneNumber
                    )
                )
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}