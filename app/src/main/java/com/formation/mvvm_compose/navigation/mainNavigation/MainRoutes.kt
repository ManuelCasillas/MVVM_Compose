package com.formation.mvvm_compose.navigation.mainNavigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.formation.mvvm_compose.navigation.mainNavigation.CustomNavType.serializableEnum
import com.formation.mvvm_compose.navigation.mainNavigation.CustomNavType.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed class MainRoutes {


    //CHARACTER DETAIL SCREEN

    @Serializable
    data class CharacterDetailScreen(
        val character: CharacterDetail,
        val characterEnum: CharacterEnum,
        val characterID: Int
    ){
        companion object {
            val typeMap = mapOf(
                typeOf<CharacterDetail>() to serializableType<CharacterDetail>(),
                typeOf<CharacterEnum>() to serializableEnum<CharacterEnum>(),
            )

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<CharacterDetail>(typeMap)
        }
    }

    @Serializable
    data class CharacterDetail(
        val id: Int,
        val title: String?,
        val description: String,
        val thumbnail: String
    )

    enum class CharacterEnum {
        DISNEY
    }




}