package `in`.squrlabs.kmm.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PopularMovieDto(
    @SerializedName("results")
    val  popularMovies:List<MovieDto>
)