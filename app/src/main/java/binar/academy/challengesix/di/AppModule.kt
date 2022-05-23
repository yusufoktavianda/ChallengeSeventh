package binar.academy.challengesix.di

import binar.academy.challengesix.ui.home.HomeViewModel
import binar.academy.challengesix.ui.login.LoginViewModel
import binar.academy.challengesix.ui.profile.ProfileViewModel
import binar.academy.challengesix.ui.register.RegisterViewModel
import binar.academy.challengesix.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
    viewModel {
        ProfileViewModel(get())
    }
    viewModel {
       SplashViewModel(get())
    }
}