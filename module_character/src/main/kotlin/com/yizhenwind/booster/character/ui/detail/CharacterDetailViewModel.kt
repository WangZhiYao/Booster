package com.yizhenwind.booster.character.ui.detail

import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.data.domain.DeleteCharacterUseCase
import com.yizhenwind.booster.common.model.Character
import com.yizhenwind.booster.component.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/6/6
 */
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val deleteCharacterUseCase: DeleteCharacterUseCase
) : ContainerHost<CharacterDetailViewState, CharacterDetailSideEffect>, BaseViewModel() {

    override val container: Container<CharacterDetailViewState, CharacterDetailSideEffect> =
        container(CharacterDetailViewState.Init)

    fun deleteCharacter(character: Character) {
        intent {
            deleteCharacterUseCase(character)
                .catch {
                    Timber.e(it)
                    postSideEffect(CharacterDetailSideEffect.DeleteCharacterFailure(R.string.error_delete_character))
                }
                .collect { success ->
                    if (success) {
                        postSideEffect(CharacterDetailSideEffect.DeleteCharacterSuccess)
                    } else {
                        postSideEffect(CharacterDetailSideEffect.DeleteCharacterFailure(R.string.error_delete_character))
                    }
                }
        }
    }
}