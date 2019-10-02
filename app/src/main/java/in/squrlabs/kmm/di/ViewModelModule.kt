package `in`.squrlabs.kmm.di

import `in`.squrlabs.kmm.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(movieRepository = get()) }
}