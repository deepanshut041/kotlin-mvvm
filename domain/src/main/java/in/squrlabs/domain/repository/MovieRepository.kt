package `in`.squrlabs.domain.repository

import `in`.squrlabs.domain.model.MovieModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface MovieRepository {
    // Suspend is used to await the result from Deferred
    fun getMovies(pageNo: Int = 1): Completable
    fun loadMovies(): Flowable<List<MovieModel>>
    fun sync(): Completable
}