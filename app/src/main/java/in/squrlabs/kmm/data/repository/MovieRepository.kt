package `in`.squrlabs.kmm.data.repository

import `in`.squrlabs.kmm.data.remote.dto.MovieDto
import `in`.squrlabs.kmm.data.remote.dto.PopularMovieDto
import `in`.squrlabs.kmm.data.remote.endpoint.MovieEndpoint
import `in`.squrlabs.kmm.data.remote.util.DtoResponse
import java.lang.Exception

interface MovieRepository {
    // Suspend is used to await the result from Deferred
    suspend fun getMovies(pageNo:Int = 1): DtoResponse<PopularMovieDto>
    suspend fun getMovie(id:Long): DtoResponse<MovieDto>
}

class MovieRepositoryImpl(private val movieEndpoint: MovieEndpoint): MovieRepository{

    override suspend fun getMovies(pageNo:Int): DtoResponse<PopularMovieDto> {
       return try {
           val result = movieEndpoint.loadPopularMovies(pageNo).await()
           DtoResponse.Success(result)
       } catch (ex: Exception){
           DtoResponse.Error(ex)
       }
    }

    override suspend fun getMovie(id:Long): DtoResponse<MovieDto> {
        return try {
            val result = movieEndpoint.loadMovie(id).await()
            DtoResponse.Success(result)
        } catch (ex: Exception){
            DtoResponse.Error(ex)
        }
    }

}