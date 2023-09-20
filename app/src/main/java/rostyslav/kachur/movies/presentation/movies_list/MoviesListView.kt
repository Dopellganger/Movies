package rostyslav.kachur.movies.presentation.movies_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import coil.compose.AsyncImage
import coil.request.ImageRequest
import rostyslav.kachur.movies.R
import rostyslav.kachur.movies.presentation.MovieUI
import rostyslav.kachur.movies.presentation.main.MainViewModel
import rostyslav.kachur.movies.presentation.navigation.MoviesRoute

@Composable
fun MoviesListView(
    viewModelStore: ViewModelStoreOwner,
    mainViewModel: MainViewModel = hiltViewModel(viewModelStore),
    viewModel: MoviesListViewModel = hiltViewModel()
) {
    val state = viewModel.viewState
    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenLoaded()
    }
    MoviesListContent(
        state = state,
        onListItemClick = remember {
            { id ->
                mainViewModel.navigateTo(MoviesRoute.MoviesDetail(id))
            }
        },
        loadNextItemsIfNeed = remember {
            { id ->
                viewModel.loadNextItems(id)
            }
        }
    )
}

@Composable
private fun MoviesListContent(
    state: MoviesListViewState,
    onListItemClick: (Int) -> Unit,
    loadNextItemsIfNeed: (Int) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        itemsIndexed(state.movies) { index, movie ->
            loadNextItemsIfNeed(index)
            MovieItemView(movie, onListItemClick)
        }
        item {
            LoadingItem(show = state.inProgress)
            ErrorItem(error = state.error)
            ReachedEndItem(reachedEnd = state.endReached)
        }
    }
}

@Composable
private fun LoadingItem(show: Boolean) {
    if (show) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun ErrorItem(error: String?) {
    if (error != null) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = error,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun ReachedEndItem(reachedEnd: Boolean) {
    if (reachedEnd) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.reached_the_end),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun MovieItemView(
    movie: MovieUI,
    onListItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .shadow(5.dp, RoundedCornerShape(2.dp))
            .clip(RoundedCornerShape(2.dp))
            .clickable { onListItemClick.invoke(movie.id) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = movie.title, maxLines = 1,
                fontSize = 10.sp, overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = movie.releaseDate.year.toString(), maxLines = 1,
                fontSize = 10.sp, overflow = TextOverflow.Ellipsis
            )
        }
    }
}
