package com.formation.mvvm_compose.screens.home.characters

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.formation.domain.model.Character

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MarvelListItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    marvelItem: Character,
    onCharacterFavoriteClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    var characterMarkAsFavroite by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            AsyncImage(
                model = marvelItem.thumbnail,
                contentDescription = marvelItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${marvelItem.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500)
                        }
                    )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = marvelItem.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .weight(1f)
                    .sharedElement(
                        state = rememberSharedContentState(key = "text/${marvelItem.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500)
                        }
                    )

            )

            IconToggleButton(
                checked = characterMarkAsFavroite,
                onCheckedChange = { characterMarkAsFavroite = it },
            ) {
                Crossfade(targetState = characterMarkAsFavroite, label = "") { isFavorite ->
                    onCharacterFavoriteClicked(isFavorite)

                    val favoriteImage = if (isFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    }

                    Icon(
                        imageVector = favoriteImage,
                        contentDescription = null,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "icon/${marvelItem.id}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 500)
                                }
                            )
                    )


                }
            }
        }
    }
}