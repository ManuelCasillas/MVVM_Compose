package com.formation.mvvm_compose.screens.home.characters.characterDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.domain.model.Character
import com.formation.domain.usecase.GetCharacterByIDUseCase
import com.formation.mvvm_compose.utils.getStringFromError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersDetailsViewModel(
    private val getCharacterByIDUseCase: GetCharacterByIDUseCase
): ViewModel() {

    private val _state: MutableStateFlow<CharacterDetailsState> =
        MutableStateFlow(CharacterDetailsState.Loading)
    val state = _state.asStateFlow()


    fun initViewModel(characterId: Int){
        loadCharacterById(characterId)
    }

    private fun loadCharacterById(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterByIDUseCase.invoke(characterId)
                .onSuccess { character ->
                    _state.update {
                        CharacterDetailsState.Success(character)
                    }
                }
                .onFailure { error ->
                    _state.update {
                        CharacterDetailsState.Failure(getStringFromError(error))
                    }
                }
        }
    }



}

sealed class CharacterDetailsState {
    data object Loading: CharacterDetailsState()
    data class Success(val character: Character): CharacterDetailsState()
    data class Failure(val msg: String): CharacterDetailsState()
}
