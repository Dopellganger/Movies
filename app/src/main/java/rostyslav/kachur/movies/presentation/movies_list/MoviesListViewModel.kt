package rostyslav.kachur.movies.presentation.movies_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rostyslav.kachur.movies.domain.MovieInterceptor
import rostyslav.kachur.movies.presentation.toUI
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val movieInterceptor: MovieInterceptor
) : ViewModel() {

    private val _viewState = mutableStateOf(MoviesListViewState())
    val viewState get() = _viewState.value

    fun onScreenLoaded() {
        loadNextItems()
    }

    fun loadNextItems(itemId: Int = 0) {
        if (itemId >= viewState.movies.size - 1 && !viewState.endReached && !viewState.inProgress) {
            viewModelScope.launch {
                reduce { inProgress = error == null }
                movieInterceptor.getMovies(viewState.currentPage).onSuccess {
                    reduce {
                        val items = it.map { it.toUI() }
                        movies = movies + items
                        error = null
                        currentPage += 1
                        endReached = items.isEmpty()
                    }
                }.onFailure { exception ->
                    reduce {
                        error = exception.message
                    }
                }
                reduce { inProgress = false }
            }
        }
    }

    private fun reduce(block: MoviesListViewState.() -> Unit) {
        val copy = viewState.copy()
        copy.block()
        _viewState.value = copy
    }
}
