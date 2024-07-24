package com.formation.mvvm_compose.di

import com.formation.mvvm_compose.login.LoginViewModel
import com.formation.mvvm_compose.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModules = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
}

val useCaseModules = module { }

val repositoryModules = module { }

val apiModules = module { }





