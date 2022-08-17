package com.yizhenwind.booster.component.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yizhenwind.booster.component.ext.viewBinding

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
abstract class BaseActivity<out VB : ViewBinding>(bindingInflater: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    protected val binding: VB by viewBinding(bindingInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (showBack()) {
            setupHomeButton()
        }

        init()
    }

    protected open fun setupHomeButton() {
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (showBack() && item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    abstract fun showBack(): Boolean

    abstract fun init()
}