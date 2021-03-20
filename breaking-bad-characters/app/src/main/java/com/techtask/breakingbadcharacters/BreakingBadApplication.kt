package com.techtask.breakingbadcharacters

import android.app.Application
import com.techtask.breakingbadcharacters.common.di.app.AppComponent
import com.techtask.breakingbadcharacters.common.di.app.AppModule
import com.techtask.breakingbadcharacters.common.di.app.DaggerAppComponent

class BreakingBadApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
