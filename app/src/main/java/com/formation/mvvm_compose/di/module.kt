package com.formation.mvvm_compose.di

import androidx.room.Room
import com.formation.data.dataSource.createRetrofit
import com.formation.data.database.CharacterDatabase
import com.formation.data.repository.CharacterRepositoryImpl
import com.formation.domain.repository.CharacterRepository
import com.formation.domain.usecase.GetCharacterByIDUseCase
import com.formation.domain.usecase.GetCharactersFavoritesListUseCase
import com.formation.domain.usecase.GetCharactersListUseCase
import com.formation.domain.usecase.SaveCharactersUseCase
import com.formation.domain.usecase.ToggleCharacterFavoriteUseCase
import com.formation.mvvm_compose.screens.home.characters.CharactersViewModel
import com.formation.mvvm_compose.screens.home.characters.characterDetails.CharactersDetailsViewModel
import com.formation.mvvm_compose.screens.login.LoginViewModel
import com.formation.mvvm_compose.screens.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModules = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { CharactersViewModel(get(),get(),get()) }
    viewModel { CharactersDetailsViewModel(get()) }
}

val useCaseModules = module {
    factory { GetCharactersListUseCase(get()) }
    factory { ToggleCharacterFavoriteUseCase(get()) }
    factory { SaveCharactersUseCase(get()) }
    factory { GetCharactersFavoritesListUseCase(get()) }
    factory { GetCharacterByIDUseCase(get()) }
}

val repositoryModules = module {
    factory<CharacterRepository>{ CharacterRepositoryImpl(get(), get()) }
}

val apiModules = module {
    single { createRetrofit() }
}

val database = module  {
    single { Room.databaseBuilder(get(), CharacterDatabase::class.java, "app_database").build() }
    single { get<CharacterDatabase>().characterDao() }
}





