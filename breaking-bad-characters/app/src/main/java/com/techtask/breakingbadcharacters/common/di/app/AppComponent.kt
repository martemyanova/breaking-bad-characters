package com.techtask.breakingbadcharacters.common.di.app

import com.techtask.breakingbadcharacters.common.di.activity.ActivityComponent
import com.techtask.breakingbadcharacters.data.di.DataModule
import com.techtask.common.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun activityComponent(): ActivityComponent
}
