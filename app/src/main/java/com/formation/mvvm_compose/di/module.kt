package com.formation.mvvm_compose.di

import com.formation.mvvm_compose.login.LoginViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModules = module {
    viewModel { LoginViewModel() }
}

val useCaseModules = module { }

val repositoryModules = module { }

val apiModules = module { }





