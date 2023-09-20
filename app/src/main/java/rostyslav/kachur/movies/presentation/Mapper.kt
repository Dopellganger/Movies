package rostyslav.kachur.movies.presentation

import rostyslav.kachur.movies.domain.MovieDetailDomain
import rostyslav.kachur.movies.domain.MovieDomain

fun MovieDomain.toUI() = MovieUI(
    id = this.id,
    posterPath = this.posterPath,
    title = this.title,
    releaseDate = this.releaseDate
)

fun MovieDetailDomain.toUI() = MovieDetailUI(
    id = this.id,
    posterPath = this.posterPath,
    title = this.title,
    releaseDate = this.releaseDate,
    rating = this.rating,
    runtime = this.runtime,
    budget = this.budget,
    revenue = this.revenue,
    overview = this.overview
)
