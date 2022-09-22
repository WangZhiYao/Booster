package com.yizhenwind.booster.common.constant

/**
 * 结算周期
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
enum class BillingCycle(val value: String) {

    TIMES("一次性"),

    MONTHLY("月付"),

    SEASON("包赛季")
}