package rostyslav.kachur.movies.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getMovies(@Query("page") id: Int): Movies

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetail
}