package com.yizhenwind.booster.home.ui.customer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yizhenwind.booster.framework.widget.action.Action
import com.yizhenwind.booster.home.R

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/23
 */
sealed class CustomerSummaryAction(
    @DrawableRes override val icon: Int,
    @StringRes override val content: Int
) : Action {

    object CreateCharacter : CustomerSummaryAction(
        R.drawable.ic_round_add_reaction_white_24dp,
        R.string.action_create_character
    )

    object CreateOrder : CustomerSummaryAction(
        R.drawable.ic_baseline_post_add_white_24dp,
        R.string.action_create_order
    )

    object Delete : CustomerSummaryAction(
        R.drawable.ic_round_delete_white_24dp,
        R.string.action_delete_customer
    )

}