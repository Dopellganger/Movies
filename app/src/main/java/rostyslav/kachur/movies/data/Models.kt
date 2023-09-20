package rostyslav.kachur.movies.data

import com.google.gson.annotations.SerializedName

data class Movies(
    val results: List<Movie>
)

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String
)

data class MovieDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("revenue")
    val revenue: Long,
    @SerializedName("overview")
    val overview: String
)
