package rostyslav.kachur.movies.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rostyslav.kachur.movies.presentation.core.NavigationEvent
import rostyslav.kachur.movies.presentation.core.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _viewState = mutableStateOf(MainViewState())
    val viewState get() = _viewState.value

    fun navigateTo(newDestination: Route) {
        reduce {
            subRoute = NavigationEvent(newDestination)
        }
    }

    private fun reduce(block: MainViewState.() -> Unit) {
        val copy = viewState.copy()
        copy.block()
        _viewState.value = copy
    }
}
