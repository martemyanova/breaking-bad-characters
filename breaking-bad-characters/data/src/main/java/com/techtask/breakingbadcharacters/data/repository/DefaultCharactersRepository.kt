package com.techtask.breakingbadcharacters.data.repository

import com.techtask.breakingbadcharacters.data.cache.LocalDataSource
import com.techtask.breakingbadcharacters.data.remote.datasource.RemoteDataSource
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.repository.CharactersRepository
import com.techtask.breakingbadcharacters.domain.result.Result

class DefaultCharactersRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CharactersRepository {

    override suspend fun getAllCharacters(): Result<List<Character>> {
        val cachedData = localDataSource.getAllCharacters()

        return if (cachedData != null) {
            Result.Success(cachedData)
        } else {
            remoteDataSource.getAllCharacters()
                .also {
                    if (it is Result.Success) {
                        localDataSource.saveAllCharacters(it.data)
                    }
                }
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        val cachedCharacter = localDataSource.getCharacterById(id)
        return if (cachedCharacter != null) {
            Result.Success(cachedCharacter)
        } else {
            Result.Failure()
        }
    }
}
