package com.yizhenwind.booster.component.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity 基类
 *
 * @author WangZhiYao
 * @since 2021/11/12
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRootView())
        initPage()
    }

    abstract fun getRootView(): View

    protected open fun initPage() {

    }
}