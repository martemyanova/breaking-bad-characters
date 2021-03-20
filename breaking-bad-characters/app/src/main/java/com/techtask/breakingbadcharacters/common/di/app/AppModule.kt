package com.techtask.breakingbadcharacters.common.di.app

import android.app.Application
import com.techtask.breakingbadcharacters.data.remote.RemoteDataSource
import com.techtask.breakingbadcharacters.data.repository.DefaultCharactersRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun application() = application
}
