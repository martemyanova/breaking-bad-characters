package com.techtask.breakingbadcharacters.domain.usecase

import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result
import javax.inject.Inject

class SearchCharactersByNameUseCase @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) {

    suspend fun execute(searchString: String): List<Character> {
        return when (val result = getAllCharactersUseCase.execute()) {
            is Result.Success -> {
                if (searchString.isEmpty()) {
                    result.data
                } else {
                    result.data.filter {
                        it.name.contains(searchString, true)
                    }
                }
            }
            is Result.Failure -> emptyList()
        }
    }
}
