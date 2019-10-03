package `in`.squrlabs.data.mappers

import `in`.squrlabs.data.local.entity.MovieEntity
import `in`.squrlabs.domain.model.MovieModel

class MovieMapper {

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