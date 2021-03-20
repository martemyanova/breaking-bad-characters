package com.techtask.breakingbadcharacters.data.remote

import com.techtask.breakingbadcharacters.data.remote.api.RemoteSourceApi
import com.techtask.breakingbadcharacters.domain.model.Character

class DefaultRemoteDataSource(
    private val remoteSourceApi: RemoteSourceApi
) : RemoteDataSource {

    override suspend fun getAllCharacters(): List<Character> {
        TODO("Not yet implemented")
    }
}
