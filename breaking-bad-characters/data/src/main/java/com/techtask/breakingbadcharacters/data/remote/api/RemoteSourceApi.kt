package com.techtask.breakingbadcharacters.data.remote.api

import com.techtask.breakingbadcharacters.data.remote.api.schema.CharacterSchema
import retrofit2.http.GET

interface RemoteSourceApi {

    @GET(".")
    suspend fun getAllCharacters(): List<CharacterSchema>

    companion object {
        const val BASE_URL = "https://breakingbadapi.com/api/characters"
    }
}
