package com.techtask.breakingbadcharacters.data.remote

import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result

interface RemoteDataSource {

    suspend fun getAllCharacters(): Result<List<Character>>
}
