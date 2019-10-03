package `in`.squrlabs.data.repository

import `in`.squrlabs.data.local.dao.MovieDao
import `in`.squrlabs.data.local.entity.MovieEntity
import `in`.squrlabs.data.mappers.MovieMapper
import `in`.squrlabs.data.remote.endpoint.MovieEndpoint
import `in`.squrlabs.domain.model.MovieModel
import `in`.squrlabs.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Flowable


class MovieRepositoryImpl(
    private val movieEndpoint: MovieEndpoint,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getMovies(pageNo: Int): Completable {
        return movieEndpoint.loadPopularMovies(pageNo)
            .flatMapCompletable {
                for (movieDto in it.popularMovies) {
                    val movieEntity: MovieEntity = MovieEntity.ModelMapper.from(movieDto)
                    movieDao.insert(movieEntity)
                }
                Completable.complete()
            }
    }

    override fun loadMovies(): Flowable<List<MovieModel>> {
        return movieDao.findAll().map { list ->
            list.map { em -> MovieMapper.ModelMapper.from(em) }
        }
    }

    override fun sync(): Completable {
        return getMovies(1)
            .andThen ( getMovies(2) )
            .andThen ( getMovies(3) )
            .andThen ( getMovies(4) )
            .andThen ( getMovies(5) )
    }
}