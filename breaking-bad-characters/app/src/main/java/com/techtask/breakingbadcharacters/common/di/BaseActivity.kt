package com.techtask.breakingbadcharacters.common.di

import androidx.appcompat.app.AppCompatActivity
import com.techtask.breakingbadcharacters.BreakingBadApplication

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() =
        (application as BreakingBadApplication).appComponent

    val activityComponent by lazy {
        appComponent.activityComponent()
    }

    private val presentationComponent by lazy {
        activityComponent.presentationComponent()
    }

    protected val injector get() = presentationComponent
}
