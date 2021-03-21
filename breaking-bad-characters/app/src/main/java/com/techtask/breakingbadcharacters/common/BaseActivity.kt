package com.techtask.breakingbadcharacters.common

import androidx.appcompat.app.AppCompatActivity
import com.techtask.breakingbadcharacters.BreakingBadApplication

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() =
        (application as BreakingBadApplication).appComponent

    private val activityComponent by lazy {
        appComponent.activityComponent()
    }

    val presentationComponent by lazy {
        activityComponent.presentationComponent()
    }

    protected val injector get() = presentationComponent
}
