package com.techtask.breakingbadcharacters.data.cache

import com.techtask.breakingbadcharacters.domain.model.Character

class InMemoryDataSource : LocalDataSource {

    private var characters: List<Character>? = null

    override suspend fun getAllCharacters(): List<Character>? {
        return characters
    }

    override suspend fun saveAllCharacters(characters: List<Character>) {
        this.characters = characters
    }
}
