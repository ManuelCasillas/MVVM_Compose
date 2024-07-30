package com.formation.mvvm_compose.screens.home.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.domain.error.ErrorType
import com.formation.domain.error.getString
import com.formation.domain.model.Character
import com.formation.domain.usecase.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val useCaseCharacterList: GetCharactersListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<CharacterListState> =
        MutableStateFlow(CharacterListState.Loading)
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {

            useCaseCharacterList.invoke()
                .onSuccess { characterList ->
                    _state.update {
                        CharacterListState.Success(characterList)
                    }
                }
                .onFailure { error ->
                    _state.update {
                        CharacterListState.Failure(
                            (error as? ErrorType)?.getString() ?: error.message ?: error::class.java.name
                        )
                    }
                }
        }
    }

    fun reloadButtonClicked(){
        _state.update { CharacterListState.Loading } // Show Loading
        loadCharacters()
    }

    fun characterFavoriteClicked(character: Character, isFavorite: Boolean){
        //TODO: call to room
    }
}

sealed class CharacterListState {
    data object Loading : CharacterListState()
    data class Success(val list: List<Character>) : CharacterListState()
    data class Failure(val message: String) : CharacterListState()
}