package rostyslav.kachur.movies.presentation.main

import rostyslav.kachur.movies.presentation.core.NavigationEvent
import rostyslav.kachur.movies.presentation.core.Route
import rostyslav.kachur.movies.presentation.navigation.MoviesRoute

data class MainViewState(
    var mainRoute: Route = MoviesRoute.MoviesList,
    var subRoute: NavigationEvent<Route>? = null
)
