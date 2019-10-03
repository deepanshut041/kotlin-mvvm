package `in`.squrlabs.data.di

import `in`.squrlabs.data.local.AppDatabase
import `in`.squrlabs.data.remote.endpoint.MovieEndpoint
import `in`.squrlabs.data.repository.MovieRepositoryImpl
import `in`.squrlabs.domain.repository.MovieRepository
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
        movieDao = get())
    }
}