package `in`.squrlabs.kmm.di

import `in`.squrlabs.kmm.data.remote.endpoint.MovieEndpoint
import `in`.squrlabs.kmm.data.repository.MovieRepository
import `in`.squrlabs.kmm.data.repository.MovieRepositoryImpl
import `in`.squrlabs.kmm.ui.main.MainViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val MOVIES_API_BASE_URL = "https://api.themoviedb.org/"

val appModules = module {

    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<MovieEndpoint>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = MOVIES_API_BASE_URL
        )
    }

    // Tells Koin how to create an instance of CatRepository
    factory<MovieRepository> { MovieRepositoryImpl(movieEndpoint = get()) }

    // Specific viewModel pattern to tell Koin how to build MainViewModel
    viewModel { MainViewModel(movieRepository = get()) }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()

        val url = original
            .url().newBuilder()
            .addQueryParameter("api_key", "fe56cdee4dfea0c18403e0965acfa23b")
            .build()

        val request = original.newBuilder().url(url).build()
        return@addInterceptor it.proceed(request)

    }.build()
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory, baseUrl: String): T {

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}