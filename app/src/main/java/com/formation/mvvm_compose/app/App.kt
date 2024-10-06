package com.formation.mvvm_compose.app

import android.app.Application
import com.formation.mvvm_compose.di.apiModules
import com.formation.mvvm_compose.di.database
import com.formation.mvvm_compose.di.repositoryModules
import com.formation.mvvm_compose.di.useCaseModules
import com.formation.mvvm_compose.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelModules, useCaseModules, repositoryModules, apiModules, database)
        }
    }

}