package com.formation.mvvm_compose.screens.home.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.formation.domain.model.Character
import com.formation.mvvm_compose.R
import com.formation.mvvm_compose.screens.home.characters.CharacterListState.Failure
import com.formation.mvvm_compose.screens.home.characters.CharacterListState.Loading
import com.formation.mvvm_compose.screens.home.characters.CharacterListState.Success
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersRoot(vm: CharactersViewModel = koinViewModel()){
    val state = vm.state.collectAsState().value
    Characters(state, vm::reloadButtonClicked, vm::characterFavoriteClicked)
}

@Composable
fun Characters(
    state: CharacterListState,
    reloadButtonClicked: () -> Unit,
    characterFavoriteClicked: (Character, Boolean) -> Unit
) {
    when (state) {
        is Loading -> { ShowLoadingView() }
        is Success -> { CharactersList(state.list, characterFavoriteClicked) }
        is Failure -> { ReloadButton(reloadButtonClicked) }
    }
}

@Composable
private fun ShowLoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator()
        Text(stringResource(R.string.home_progress_indicator_title), modifier = Modifier.padding(top = 6.dp))
    }
}

@Composable
private fun CharactersList(
    characterList: List<Character>,
    characterFavoritedClicked: (Character, Boolean) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (characterList.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(characterList) { character ->
                    MarvelListItem(
                        marvelItem = character,
                        onCharacterFavoriteClicked = { isFavorite ->
                            characterFavoritedClicked(character, isFavorite)
                        },
                        modifier = Modifier.clickable { }
                    )
                }
            }
        }
    }
}

@Composable
private fun ReloadButton(reloadButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            stringResource(R.string.home_error_message),
            textAlign = TextAlign.Center
        )

        Button(onClick = {
            reloadButtonClicked()
        }) {
            Text(text = stringResource(R.string.home_reload_message))
        }
    }
}