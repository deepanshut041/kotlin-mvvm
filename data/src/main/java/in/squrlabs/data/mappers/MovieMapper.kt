package `in`.squrlabs.data.mappers

import `in`.squrlabs.data.local.entity.MovieEntity
import `in`.squrlabs.domain.model.MovieModel


fun MovieEntity.from() = MovieModel(
    posterPath = posterPath,
    adult = adult,
    overview = overview,
    releaseDate = releaseDate,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    video = video,
    voteAverage = voteAverage
)
