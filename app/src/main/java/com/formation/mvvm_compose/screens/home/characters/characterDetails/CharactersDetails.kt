package com.formation.mvvm_compose.screens.home.characters.characterDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.formation.domain.model.Character
import com.formation.mvvm_compose.navigation.mainNavigation.MainRoutes
import com.formation.mvvm_compose.utils.ShowLoadingView
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailsRoot(vm: CharactersDetailsViewModel = koinViewModel(), args: MainRoutes.CharacterDetailScreen){
    val state = vm.state.collectAsState().value
    val characterId = args.characterID
   CharacterDetails(state, vm::initViewModel, characterId)
}

@Composable
fun CharacterDetails(
    state: CharacterDetailsState,
    initViewModel: (Int) -> Unit,
    characterId: Int
) {

    LaunchedEffect(Unit) {
        initViewModel(characterId)
    }

    when (state) {
        CharacterDetailsState.Loading -> ShowLoadingView()
        is CharacterDetailsState.Success -> ShowCharacterInfo(state.character)
        is CharacterDetailsState.Failure -> ShowErrorMessage(state.msg)
    }
}


@Composable
fun ShowCharacterInfo(characterInfo: Character){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¿Este character es favorito? ->  ${characterInfo.favorite}", textAlign = TextAlign.Center)
    }

}

@Composable
fun ShowErrorMessage(msg: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(msg, textAlign = TextAlign.Center)
    }
}


