package com.techtask.breakingbadcharacters.domain.usecase

import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.repository.CharactersRepository
import com.techtask.breakingbadcharacters.domain.result.Result
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend fun execute(): Result<List<Character>> {
        return repository.getAllCharacters()
    }
}
