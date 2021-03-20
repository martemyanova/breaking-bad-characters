package com.techtask.breakingbadcharacters.common.di.presentation

import com.techtask.breakingbadcharacters.MainActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {
    fun inject(activity: MainActivity)
}
