package com.techtask.breakingbadcharacters.data.di

import com.techtask.breakingbadcharacters.data.cache.InMemoryDataSource
import com.techtask.breakingbadcharacters.data.cache.LocalDataSource
import com.techtask.breakingbadcharacters.data.remote.datasource.DefaultRemoteDataSource
import com.techtask.breakingbadcharacters.data.remote.datasource.RemoteDataSource
import com.techtask.breakingbadcharacters.data.remote.api.RemoteSourceApi
import com.techtask.breakingbadcharacters.data.repository.DefaultCharactersRepository
import com.techtask.breakingbadcharacters.domain.repository.CharactersRepository
import com.techtask.common.AppScope
import com.techtask.common.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @AppScope
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteSourceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @AppScope
    @Provides
    fun remoteSourceApi(retrofit: Retrofit): RemoteSourceApi =
        retrofit.create(RemoteSourceApi::class.java)

    @AppScope
    @Provides
    fun remoteDataSource(
        remoteSourceApi: RemoteSourceApi,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): RemoteDataSource =
        DefaultRemoteDataSource(remoteSourceApi, coroutineDispatcherProvider)

    @AppScope
    @Provides
    fun localDataSource(): LocalDataSource = InMemoryDataSource()

    @AppScope
    @Provides
    fun charactersRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): CharactersRepository =
        DefaultCharactersRepository(remoteDataSource, localDataSource)
}
