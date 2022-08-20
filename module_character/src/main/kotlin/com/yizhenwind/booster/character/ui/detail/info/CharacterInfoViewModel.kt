package com.yizhenwind.booster.character.ui.detail.info

import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.data.domain.GetCharacterByIdUseCase
import com.yizhenwind.booster.common.ext.ifNullOrElse
import com.yizhenwind.booster.component.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/8/17
 */
@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ContainerHost<CharacterInfoViewState, CharacterInfoSideEffect>, BaseViewModel() {

    override val container: Container<CharacterInfoViewState, CharacterInfoSideEffect> =
        container(CharacterInfoViewState.Init)

    fun getCharacterById(characterId: Long) {
        intent {
            getCharacterByIdUseCase(characterId)
                .collect { character ->
                    character.ifNullOrElse({
                        postSideEffect(CharacterInfoSideEffect.GetCharacterFailure(R.string.error_get_character_failure))
                    }, {
                        reduce {
                            CharacterInfoViewState.GetCharacterSuccess(it)
                        }
                    })
                }
        }
    }
}