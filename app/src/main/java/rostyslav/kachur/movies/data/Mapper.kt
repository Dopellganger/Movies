package rostyslav.kachur.movies.data

import rostyslav.kachur.movies.domain.MovieDetailDomain
import rostyslav.kachur.movies.domain.MovieDomain
import java.time.LocalDate

fun Movie.toDomain(basePosterPath: String) = MovieDomain(
    id = this.id,
    posterPath = basePosterPath + this.posterPath,
    title = this.title,
    releaseDate = LocalDate.parse(this.releaseDate)
)

fun MovieDetail.toDomain(basePosterPath: String) = MovieDetailDomain(
    id = this.id,
    posterPath = basePosterPath + this.posterPath,
    title = this.title,
    releaseDate = this.releaseDate,
    rating = this.rating,
    runtime = this.runtime,
    budget = this.budget,
    revenue = this.revenue,
    overview = this.overview,
)
