package com.techtask.breakingbadcharacters.data.remote

import com.techtask.breakingbadcharacters.data.remote.api.RemoteSourceApi
import com.techtask.breakingbadcharacters.data.remote.api.schema.CharacterSchema
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultRemoteDataSource(
    private val remoteSourceApi: RemoteSourceApi
) : RemoteDataSource {

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = remoteSourceApi.getAllCharacters()
                val charactersData = response.body()

                if (response.isSuccessful && charactersData != null && charactersData.isNotEmpty()) {
                    return@withContext Result.Success(
                        charactersData.parse()
                    )
                } else {
                    return@withContext Result.Failure()
                }
            } catch (t: Throwable) {
                return@withContext Result.Failure(t)
            }
        }
    }

    private fun List<CharacterSchema>.parse(): List<Character> =
        this.map { it.parse() }

    private fun CharacterSchema.parse() =
        Character(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl
        )
}
