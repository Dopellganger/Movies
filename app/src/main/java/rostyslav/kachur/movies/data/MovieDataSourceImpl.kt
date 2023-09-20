package rostyslav.kachur.movies.data

import rostyslav.kachur.movies.domain.MovieDataSource
import rostyslav.kachur.movies.domain.MovieDetailDomain
import rostyslav.kachur.movies.domain.MovieDomain
import java.io.IOException
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDataSource {

    override suspend fun getMovies(page: Int): Result<List<MovieDomain>> {
        return try {
            Result.success(movieApi.getMovies(page).results.map { it.toDomain(POSTER_BASE_URL) })
        } catch (e: Exception) {
            if (e is IOException) {
                Result.failure(Exception("No Internet Connection"))
            } else {
                Result.failure(Exception("Something went wrong"))
            }
        }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetailDomain> {
        return try {
            Result.success(movieApi.getMovieDetail(id).toDomain(POSTER_BASE_URL))
        } catch (e: Exception) {
            if (e is IOException) {
                Result.failure(Exception("No Internet Connection"))
            } else {
                Result.failure(Exception("Something went wrong"))
            }
        }
    }

    companion object {
        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
    }
}
