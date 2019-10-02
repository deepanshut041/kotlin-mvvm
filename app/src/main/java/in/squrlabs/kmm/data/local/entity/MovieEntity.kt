package `in`.squrlabs.kmm.data.local.entity

import `in`.squrlabs.kmm.data.remote.dto.MovieDto
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "popularity")
    val popularity:Float,

    @ColumnInfo(name = "vote_count")
    val voteCount:Int,

    @ColumnInfo(name = "video")
    val video:Boolean,

    @ColumnInfo(name = "vote_average")
    val voteAverage: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ){

    object ModelMapper {
        fun from(movieDto: MovieDto) = MovieEntity(
            posterPath = movieDto.posterPath,
            adult = movieDto.adult,
            overview = movieDto.overview,
            releaseDate = movieDto.releaseDate,
            originalTitle = movieDto.originalTitle,
            originalLanguage = movieDto.originalLanguage,
            title = movieDto.title,
            backdropPath = movieDto.backdropPath,
            popularity = movieDto.popularity,
            voteCount = movieDto.voteCount,
            video = movieDto.video,
            voteAverage = movieDto.voteAverage
        )
    }
}