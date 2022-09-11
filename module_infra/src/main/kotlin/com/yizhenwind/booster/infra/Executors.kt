package com.yizhenwind.booster.infra

import java.util.concurrent.Executors

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/8
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(runnable: () -> Unit) {
    IO_EXECUTOR.execute(runnable)
}