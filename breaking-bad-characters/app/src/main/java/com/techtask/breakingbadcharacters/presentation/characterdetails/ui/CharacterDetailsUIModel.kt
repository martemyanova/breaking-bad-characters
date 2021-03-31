package com.techtask.breakingbadcharacters.presentation.characterdetails.ui

import androidx.annotation.StringRes

data class CharacterDetailsUIModel(
    val imageUrl: String,
    val details: List<CharacterDetailUIModel>
)

data class CharacterDetailUIModel(
    @StringRes val titleResId: Int,
    val text: String
)

