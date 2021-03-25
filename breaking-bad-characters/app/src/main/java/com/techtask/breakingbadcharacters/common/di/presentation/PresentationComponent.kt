package com.techtask.breakingbadcharacters.common.di.presentation

import com.techtask.breakingbadcharacters.MainActivity
import com.techtask.breakingbadcharacters.presentation.characterdetails.CharacterDetailsFragment
import com.techtask.breakingbadcharacters.presentation.characterslist.CharactersListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [ViewModelModule::class])
interface PresentationComponent {
    fun inject(fragment: CharactersListFragment)
    fun inject(fragment: CharacterDetailsFragment)
    fun inject(activity: MainActivity)
}
