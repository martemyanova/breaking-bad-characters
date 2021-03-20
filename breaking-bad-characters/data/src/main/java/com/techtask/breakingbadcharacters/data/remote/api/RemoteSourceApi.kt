package com.techtask.breakingbadcharacters.data.remote.api

import com.techtask.breakingbadcharacters.data.remote.api.schema.CharacterSchema
import retrofit2.Response
import retrofit2.http.GET

interface RemoteSourceApi {

    @GET(".")
    suspend fun getAllCharacters(): Response<List<CharacterSchema>>

    companion object {
        const val BASE_URL = "https://breakingbadapi.com/api/characters/"
    }
}
