package `in`.squrlabs.kmm

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(movieRepository = get()) }
}