package `in`.squrlabs.kmm.data.repository

import `in`.squrlabs.kmm.data.local.dao.MovieDao
import `in`.squrlabs.kmm.data.local.entity.MovieEntity
import `in`.squrlabs.kmm.data.local.util.DbResponse
import `in`.squrlabs.kmm.data.remote.dto.MovieModel
import `in`.squrlabs.kmm.data.remote.endpoint.MovieEndpoint
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.lang.Exception

interface MovieRepository {
    // Suspend is used to await the result from Deferred
    suspend fun getMovies(pageNo: Int = 1): Boolean

    fun loadMovies(): LiveData<List<MovieModel>>
}

class MovieRepositoryImpl(
    private val movieEndpoint: MovieEndpoint,
    private val movieDao: MovieDao) : MovieRepository {

    override suspend fun getMovies(pageNo: Int): Boolean {
        return try {
            val result = movieEndpoint.loadPopularMovies(pageNo).await()
            for (movieDto in result.popularMovies) {
                val movieEntity:MovieEntity = MovieEntity.ModelMapper.from(movieDto)
                movieDao.insert(movieEntity)
            }
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun loadMovies(): LiveData<List<MovieModel>> {
        return Transformations.map(movieDao.findAll()) {
            movieList -> movieList.map { em -> MovieModel.ModelMapper.from(em) }
        }
    }

}