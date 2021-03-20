package com.techtask.breakingbadcharacters.domain.repository

import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result

interface CharactersRepository {

    suspend fun getAllCharacters(): Result<List<Character>>
}
