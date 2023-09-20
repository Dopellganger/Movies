package rostyslav.kachur.movies.domain

import java.time.LocalDate

data class MovieDomain(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: LocalDate
)

data class MovieDetailDomain(
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
