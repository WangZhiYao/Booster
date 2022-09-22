package com.yizhenwind.booster.framework.ext

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.yizhenwind.booster.framework.base.FragmentArgsLazy
import com.yizhenwind.booster.framework.base.IFragmentArgs

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
inline fun <reified Args : IFragmentArgs> Fragment.fragmentArgs(): FragmentArgsLazy<Args> =
    FragmentArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("Fragment $this has null arguments")
    }

inline fun Fragment.registerMenu(
    @MenuRes menuRes: Int,
    crossinline onMenuItemSelected: (MenuItem) -> Boolean
) {
    requireActivity().addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuRes, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
            onMenuItemSelected.invoke(menuItem)

    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}