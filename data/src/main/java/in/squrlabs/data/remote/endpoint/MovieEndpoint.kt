package `in`.squrlabs.data.remote.endpoint

import `in`.squrlabs.data.remote.dto.MovieDto
import `in`.squrlabs.data.remote.dto.PopularMovieDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieEndpoint {
    @GET("3/discover/movie")
    fun loadPopularMovies(@Query("page") page: Int): Deferred<PopularMovieDto>

    @GET("3/movie/{id}")
    fun loadMovie(@Path("id") id: Long): Deferred<MovieDto>
}