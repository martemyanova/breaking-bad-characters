package com.techtask.breakingbadcharacters.data.cache

import com.techtask.breakingbadcharacters.domain.model.Character

class InMemoryDataSource : LocalDataSource {

    private var characters: Map<Int, Character>? = null

    override suspend fun getAllCharacters(): List<Character>? {
        return characters?.values?.toList()
    }

    override suspend fun saveAllCharacters(characters: List<Character>) {
        this.characters = characters.associateBy(
            {it.id}, {it}
        )
    }

    override suspend fun getCharacterById(id: Int): Character? {
        return characters?.get(id)
    }
}
