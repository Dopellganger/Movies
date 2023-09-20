package rostyslav.kachur.movies.domain

interface MovieDataSource {

    suspend fun getMovies(page: Int): Result<List<MovieDomain>>

    suspend fun getMovieDetail(id: Int): Result<MovieDetailDomain>
}
