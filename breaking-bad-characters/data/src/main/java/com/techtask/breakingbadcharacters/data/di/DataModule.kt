package com.techtask.breakingbadcharacters.data.di

import com.techtask.breakingbadcharacters.data.remote.DefaultRemoteDataSource
import com.techtask.breakingbadcharacters.data.remote.RemoteDataSource
import com.techtask.breakingbadcharacters.data.remote.api.RemoteSourceApi
import com.techtask.breakingbadcharacters.data.repository.DefaultCharactersRepository
import com.techtask.breakingbadcharacters.domain.repository.CharactersRepository
import com.techtask.common.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteSourceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun remoteSourceApi(retrofit: Retrofit): RemoteSourceApi =
        retrofit.create(RemoteSourceApi::class.java)

    @Provides
    fun remoteDataSource(
        remoteSourceApi: RemoteSourceApi,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): RemoteDataSource =
        DefaultRemoteDataSource(remoteSourceApi, coroutineDispatcherProvider)

    @Provides
    fun charactersRepository(remoteDataSource: RemoteDataSource): CharactersRepository =
        DefaultCharactersRepository(remoteDataSource)
}
