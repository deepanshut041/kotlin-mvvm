package `in`.squrlabs.data.domain

import `in`.squrlabs.data.local.entity.MovieEntity

data class MovieModel(
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity:Float,
    val voteCount:Int,
    val video:Boolean,
    val voteAverage: String ){

    object ModelMapper {
        fun from(movieEntity: MovieEntity) = MovieModel(
            posterPath = movieEntity.posterPath,
            adult = movieEntity.adult,
            overview = movieEntity.overview,
            releaseDate = movieEntity.releaseDate,
            originalTitle = movieEntity.originalTitle,
            originalLanguage = movieEntity.originalLanguage,
            title = movieEntity.title,
            backdropPath = movieEntity.backdropPath,
            popularity = movieEntity.popularity,
            voteCount = movieEntity.voteCount,
            video = movieEntity.video,
            voteAverage = movieEntity.voteAverage
        )
    }
}
