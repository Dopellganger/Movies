package rostyslav.kachur.movies.presentation.core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rostyslav.kachur.movies.BuildConfig
import rostyslav.kachur.movies.data.MovieApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideRequestInterceptor() = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)
}