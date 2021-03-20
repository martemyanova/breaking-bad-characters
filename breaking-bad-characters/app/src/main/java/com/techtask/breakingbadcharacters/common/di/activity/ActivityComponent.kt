package com.techtask.breakingbadcharacters.common.di.activity

import com.techtask.breakingbadcharacters.common.di.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun presentationComponent(): PresentationComponent
}
