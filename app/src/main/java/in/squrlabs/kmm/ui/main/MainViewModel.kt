package `in`.squrlabs.kmm.ui.main

import `in`.squrlabs.kmm.data.remote.dto.MovieDto
import `in`.squrlabs.kmm.data.remote.dto.PopularMovieDto
import `in`.squrlabs.kmm.data.remote.util.DtoResponse
import `in`.squrlabs.kmm.data.repository.MovieRepository
import `in`.squrlabs.kmm.util.SingleLiveEvent
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
    val moviesList = MutableLiveData<List<MovieDto>>()
    val showError = SingleLiveEvent<String>()

    fun loadMovies() {
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO){ movieRepository.getMovies() }

            showLoading.value = false

            when(result) {
                is DtoResponse.Success -> moviesList.value = result.data.popularMovies
                is DtoResponse.Error -> showError.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}