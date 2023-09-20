package rostyslav.kachur.movies.presentation.movies_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rostyslav.kachur.movies.domain.MovieInterceptor
import rostyslav.kachur.movies.presentation.toUI
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    private val movieInterceptor: MovieInterceptor
) : ViewModel() {

    private val _viewState = mutableStateOf(MoviesDetailViewState())
    val viewState get() = _viewState.value

    fun onScreenLoaded(id: Int) {
        viewModelScope.launch {
            reduce { inProgress = true }
            movieInterceptor.getMovieDetail(id).onSuccess {
                reduce {
                    movieDetail = it.toUI()
                    error = null
                }
            }.onFailure { exception ->
                reduce {
                    error = exception.message
                }
            }
            reduce { inProgress = false }
        }
    }

    private fun reduce(block: MoviesDetailViewState.() -> Unit) {
        val copy = viewState.copy()
        copy.block()
        _viewState.value = copy
    }
}
