package com.yizhenwind.booster.component.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

/**
 *
 * @author WangZhiYao
 * @since 2022/7/11
 */
abstract class BaseMVIViewModel<STATE : IViewState, SIDE_EFFECT : ISideEffect> :
    ContainerHost<STATE, SIDE_EFFECT>, ViewModel()