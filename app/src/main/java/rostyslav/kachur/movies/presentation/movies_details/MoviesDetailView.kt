package rostyslav.kachur.movies.presentation.movies_details

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import rostyslav.kachur.movies.R
import rostyslav.kachur.movies.presentation.core.ui.LoadingProgressIndicator

@Composable
fun MoviesDetailView(
    id: Int,
    viewModel: MoviesDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state = viewModel.viewState
    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenLoaded(id)
    }
    BackHandler {
        onBackClick.invoke()
    }
    MoviesDetailContent(state = state)
}

@Composable
fun MoviesDetailContent(
    state: MoviesDetailViewState
) {
    LoadingProgressIndicator(show = state.inProgress)
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        if (state.movieDetail != null) {
            AsyncImage(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                model = state.movieDetail?.posterPath,
                contentDescription = null
            )
            Column(Modifier.padding(16.dp)) {
                Text(text = state.movieDetail?.title ?: "", fontSize = 18.sp)
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(id = R.string.more_info),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                MovieDetailRow(R.string.release_date, state.movieDetail?.releaseDate ?: "")
                MovieDetailRow(R.string.rating, state.movieDetail?.rating.toString())
                MovieDetailRow(
                    R.string.runtime,
                    stringResource(
                        id = R.string.runtime_in_minutes,
                        state.movieDetail?.runtime ?: 0
                    )
                )
                MovieDetailRow(R.string.budget, stringResource(R.string.value_in_currency, state.movieDetail?.budget ?: 0))
                MovieDetailRow(R.string.revenue, stringResource(R.string.value_in_currency, state.movieDetail?.revenue ?: 0))
                Text(
                    text = stringResource(id = R.string.overview),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = state.movieDetail?.overview ?: "", fontSize = 12.sp)
            }
        }
        state.error?.let { error ->
            Text(text = error)
        }
    }
}

@Composable
private fun MovieDetailRow(@StringRes strRes: Int, str: String) {
    Row {
        Text(text = stringResource(id = strRes), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(modifier = Modifier.padding(start = 4.dp), text = str, fontSize = 12.sp)
        Spacer(modifier = Modifier.size(4.dp))
    }
}
