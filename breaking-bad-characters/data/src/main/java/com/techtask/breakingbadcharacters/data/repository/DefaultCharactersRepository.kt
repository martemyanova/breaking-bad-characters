package com.techtask.breakingbadcharacters.data.repository

import com.techtask.breakingbadcharacters.data.remote.RemoteDataSource
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.repository.CharactersRepository

class DefaultCharactersRepository(
    private val remoteDataSource: RemoteDataSource
) : CharactersRepository {

    override suspend fun getAllCharacters(): List<Character> {
        TODO("Not yet implemented")
    }
}
