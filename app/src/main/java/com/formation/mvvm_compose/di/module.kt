package com.formation.mvvm_compose.di

import com.formation.data.dataSource.createRetrofit
import com.formation.data.repository.CharacterRepositoryImpl
import com.formation.domain.repository.CharacterRepository
import com.formation.domain.usecase.GetCharactersListUseCase
import com.formation.mvvm_compose.screens.home.characters.CharactersViewModel
import com.formation.mvvm_compose.screens.login.LoginViewModel
import com.formation.mvvm_compose.screens.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModules = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { CharactersViewModel(get()) }
}

val useCaseModules = module {
    factory { GetCharactersListUseCase(get()) }
}

val repositoryModules = module {
    factory<CharacterRepository>{ CharacterRepositoryImpl(get()) }
}

val apiModules = module {
    single { createRetrofit() }
}





