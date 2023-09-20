package rostyslav.kachur.movies.presentation.navigation

import rostyslav.kachur.movies.presentation.core.Route

sealed class MoviesRoute : Route() {
    object MoviesList : MoviesRoute() {
        override val route: String get() = "MoviesList"
    }

    class MoviesDetail(
        id: Int,
        override val route: String = Route.replace("{movieId}", id.toString())
    ) : MoviesRoute() {

        companion object {
            const val Route = "MoviesDetail/{movieId}"
            const val movieId = "movieId"
        }
    }
}