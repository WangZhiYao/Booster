package com.yizhenwind.booster.character.ui.info.detail

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
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ContainerHost<CharacterDetailViewState, CharacterDetailSideEffect>, BaseViewModel() {

    override val container: Container<CharacterDetailViewState, CharacterDetailSideEffect> =
        container(CharacterDetailViewState.Init)

    fun getCharacterById(characterId: Long) {
        intent {
            getCharacterByIdUseCase(characterId)
                .collect { character ->
                    character.ifNullOrElse({
                        postSideEffect(CharacterDetailSideEffect.GetCharacterFailure(R.string.error_get_character_failure))
                    }, {
                        reduce {
                            CharacterDetailViewState.GetCharacterSuccess(it)
                        }
                    })
                }
        }
    }
}