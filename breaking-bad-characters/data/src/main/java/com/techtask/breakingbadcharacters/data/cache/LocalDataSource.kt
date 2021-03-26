package com.techtask.breakingbadcharacters.data.cache

import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result

interface LocalDataSource {

    suspend fun getAllCharacters(): List<Character>?

    suspend fun saveAllCharacters(characters: List<Character>)

    suspend fun getCharacterById(id: Int): Character?
}
