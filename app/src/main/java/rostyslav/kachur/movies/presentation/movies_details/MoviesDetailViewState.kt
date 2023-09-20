package rostyslav.kachur.movies.presentation.movies_details

import rostyslav.kachur.movies.presentation.MovieDetailUI

data class MoviesDetailViewState(
    var movieDetail: MovieDetailUI? = null,
    var error: String? = null,
    var inProgress: Boolean = false
)
