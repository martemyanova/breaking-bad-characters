package com.techtask.breakingbadcharacters.data.remote.datasource

import com.techtask.breakingbadcharacters.data.remote.api.RemoteSourceApi
import com.techtask.breakingbadcharacters.data.remote.api.schema.CharacterSchema
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result
import com.techtask.common.CoroutineDispatcherProvider
import kotlinx.coroutines.withContext

class DefaultRemoteDataSource(
    private val remoteSourceApi: RemoteSourceApi,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : RemoteDataSource {

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return withContext(coroutineDispatcherProvider.io) {
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
            imageUrl = this.imageUrl,
            birthday = this.birthday,
            occupation = this.occupation,
            status = this.status,
            nickname = this.nickname,
            appearance = this.appearance,
            portrayed = this.portrayed,
            category = this.category,
            betterCallSaulAppearance = this.betterCallSaulAppearance
        )
}
