package binar.academy.challengesix

import android.app.Application
import binar.academy.challengesix.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    movieDataSourceModule,
                    movieRepositoryModule,
                    viewModelModule,
                    userDatabaseModule,
                    userRepository
                )
            )
        }
    }
}