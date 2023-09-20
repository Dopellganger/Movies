package rostyslav.kachur.movies.presentation.movies_list

import rostyslav.kachur.movies.presentation.MovieUI

data class MoviesListViewState(
    var movies: List<MovieUI> = listOf(),
    var error: String? = null,
    var loadingPage: Boolean = true,
    var endReached: Boolean = false,
    var currentPage: Int = 1,
    var inProgress: Boolean = false
)