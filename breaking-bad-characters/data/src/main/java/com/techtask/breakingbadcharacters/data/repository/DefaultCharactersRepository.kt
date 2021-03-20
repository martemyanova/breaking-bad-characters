package com.techtask.breakingbadcharacters.data.repository

import com.techtask.breakingbadcharacters.data.remote.RemoteDataSource
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.repository.CharactersRepository
import com.techtask.breakingbadcharacters.domain.result.Result

class DefaultCharactersRepository(
    private val remoteDataSource: RemoteDataSource
) : CharactersRepository {

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return remoteDataSource.getAllCharacters()
    }
}
