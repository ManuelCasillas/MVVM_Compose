package com.formation.mvvm_compose.screens.home.characters.characterDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
fun ShowCharacterInfo(characterInfo: Character) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState), // Apply vertical scroll
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        AsyncImage(
            model = characterInfo.thumbnail,
            contentDescription = characterInfo.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(16 / 9f)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                characterInfo.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Center)
            )


            val favoriteImage = if (characterInfo.favorite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            }
            Icon(
                imageVector = favoriteImage,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(characterInfo.description, Modifier.padding(16.dp))

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

