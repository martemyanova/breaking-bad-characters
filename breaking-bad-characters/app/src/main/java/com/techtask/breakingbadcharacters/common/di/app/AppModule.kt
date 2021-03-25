package com.techtask.breakingbadcharacters.common.di.app

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun application() = application
}
