package com.techtask.breakingbadcharacters.data.remote

import com.techtask.breakingbadcharacters.domain.model.Character

interface RemoteDataSource {
    suspend fun getAllCharacters(): List<Character>
}
