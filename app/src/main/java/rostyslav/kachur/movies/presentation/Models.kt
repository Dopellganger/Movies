package rostyslav.kachur.movies.presentation

import java.time.LocalDate

data class MovieUI(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: LocalDate
)

data class MovieDetailUI(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val runtime: Int,
    val budget: Long,
    val revenue: Long,
    val overview: String,
)
