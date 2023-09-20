package rostyslav.kachur.movies.presentation.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rostyslav.kachur.movies.data.MovieDataSourceImpl
import rostyslav.kachur.movies.domain.MovieDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceBindingModule {
    @Binds
    abstract fun bindValueEstimatesDataSource(dataSource: MovieDataSourceImpl): MovieDataSource
}
