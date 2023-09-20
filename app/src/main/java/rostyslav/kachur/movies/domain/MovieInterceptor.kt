package rostyslav.kachur.movies.domain

import javax.inject.Inject

class MovieInterceptor @Inject constructor(
    private val movieDataSource: MovieDataSource
) {

    suspend fun getMovies(page: Int): Result<List<MovieDomain>> = movieDataSource.getMovies(page)

    suspend fun getMovieDetail(id: Int): Result<MovieDetailDomain> = movieDataSource.getMovieDetail(id)
}
