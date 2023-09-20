package rostyslav.kachur.movies.presentation.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import rostyslav.kachur.movies.presentation.core.Route
import rostyslav.kachur.movies.presentation.movies_details.MoviesDetailView
import rostyslav.kachur.movies.presentation.movies_list.MoviesListView
import rostyslav.kachur.movies.presentation.navigation.MoviesRoute

@Composable
fun MainView() {
    val mainViewModel: MainViewModel = hiltViewModel()
    val viewModelsStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val navController = rememberNavController()
    val state = mainViewModel.viewState

    MainNavHost(viewModelsStoreOwner, navController, state.mainRoute)

    state.subRoute?.getContentIfNotHandled()?.let { route ->
        navController.navigate(route.route)
    }
}

@Composable
fun MainNavHost(
    viewModelsStoreOwner: ViewModelStoreOwner,
    navController: NavHostController,
    startDestination: Route
) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(MoviesRoute.MoviesList.route) {
            MoviesListView(viewModelsStoreOwner)
        }

        composable(MoviesRoute.MoviesDetail.Route) {
            val arguments = requireNotNull(it.arguments)
            val id = arguments.getString(MoviesRoute.MoviesDetail.movieId)?.toInt() ?: 0
            MoviesDetailView(id) {
                navController.popBackStack()
            }
        }
    }
}
