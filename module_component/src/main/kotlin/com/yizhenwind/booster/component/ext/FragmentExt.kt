package com.yizhenwind.booster.component.ext

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.yizhenwind.booster.component.base.IFragmentArgs

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
inline fun <reified T : IFragmentArgs> Fragment.fragmentArgs(
    crossinline deserializer: (Bundle) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        deserializer.invoke(requireArguments())
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