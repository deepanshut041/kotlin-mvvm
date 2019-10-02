package `in`.squrlabs.kmm.ui.main

import `in`.squrlabs.kmm.data.local.entity.MovieEntity
import `in`.squrlabs.kmm.data.local.util.DbResponse
import `in`.squrlabs.kmm.data.remote.dto.MovieModel
import `in`.squrlabs.kmm.data.repository.MovieRepository
import `in`.squrlabs.kmm.util.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val movieRepository: MovieRepository): ViewModel(), CoroutineScope {
    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    internal val moviesList: LiveData<List<MovieModel>>
    val showError = SingleLiveEvent<String>()

    init {
        moviesList = movieRepository.loadMovies()
    }

    fun sync() {

        launch {
            val result = withContext(Dispatchers.IO) {
                movieRepository.getMovies(1)
                movieRepository.getMovies(2)
                movieRepository.getMovies(3)
                movieRepository.getMovies(4)
            }

            if (result){
                showError.value = "Sync Completed"
            } else {
                showError.value = "Failed to Sync"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}