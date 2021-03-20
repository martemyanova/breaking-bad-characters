package com.techtask.breakingbadcharacters.domain.repository

import com.techtask.breakingbadcharacters.domain.model.Character

interface CharactersRepository {

    suspend fun getAllCharacters(): List<Character>
}
