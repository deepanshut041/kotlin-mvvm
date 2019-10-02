package `in`.squrlabs.kmm.di

import `in`.squrlabs.kmm.data.local.AppDatabase
import `in`.squrlabs.kmm.data.remote.endpoint.MovieEndpoint
import `in`.squrlabs.kmm.data.repository.MovieRepository
import `in`.squrlabs.kmm.data.repository.MovieRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single(createdAtStart = false) {
        get<AppDatabase>().getMovieDao()
    }

    single {
        get<Retrofit>().create(MovieEndpoint::class.java)
    }
    factory<MovieRepository> { MovieRepositoryImpl(movieEndpoint = get(),
        movieDao = get())}
}