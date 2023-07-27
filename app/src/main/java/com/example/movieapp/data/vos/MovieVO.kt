package com.example.movieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.persistence.typeconverters.GenreIdTypeConverter
import com.example.movieapp.persistence.typeconverters.GenreListTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")

@TypeConverters(
//    CollectionTypeConverter::class,
    GenreListTypeConverter::class,
    GenreIdTypeConverter::class,
//    ProductionCompanyConverter::class,
//    ProductionCountryConverter::class,
//    SpokenLanguageConverter::class
)



data class MovieVO(
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean?,


    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,


    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,


    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,


    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,


    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,


    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,


    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,


    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,


    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,


    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,


    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float?,


    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,


    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVO>?,


    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runTime : Int ?,

    @ColumnInfo(name = "type")
    var type: String?
)
{
    fun getRatingBaseOnFiveStars(): Float {
        return voteAverage?.div(2) ?: 0.0f
    }

    fun calculateRunTime() : String{
        val movieRunTime : String
        val hour : String = runTime?.div(60).toString()
        val min : String = runTime?.rem(60).toString()
        movieRunTime = "$hour h $min min"
        return movieRunTime
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }
}
const val  UP_COMING = "UP_COMING"
const val NOW_PLAYING = "NOW_PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP_RATED"
