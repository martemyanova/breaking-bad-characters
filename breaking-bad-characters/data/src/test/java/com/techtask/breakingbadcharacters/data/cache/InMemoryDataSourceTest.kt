package com.techtask.breakingbadcharacters.data.cache

import com.techtask.breakingbadcharacters.domain.model.Character
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class InMemoryDataSourceTest {

    private lateinit var SUT: InMemoryDataSource

    @BeforeEach
    fun setUp() {
        SUT = InMemoryDataSource()
    }

    @Test
    fun `GIVEN save characters method were called previously WHEN get method executed THEN return saved characters`() = runBlocking {
        //given

        //when
        SUT.saveAllCharacters(fakeCharacterList)
        val actual = SUT.getAllCharacters()

        //then
        assertArrayEquals(fakeCharacterList.toTypedArray(), actual?.toTypedArray())
    }

    @Test
    fun `GIVEN save characters method were called previously WHEN get by id method executed THEN return correct character`() = runBlocking {
        //given

        //when
        SUT.saveAllCharacters(fakeCharacterList)
        val actual1 = SUT.getCharacterById(1)
        val actual2 = SUT.getCharacterById(133)

        //then
        assertEquals(fakeCharacter1, actual1)
        assertEquals(fakeCharacter2, actual2)
    }

    companion object {

        private val fakeCharacter1 = Character(
            id = 1,
            name = "Fake Character",
            imageUrl = "image_url1",
            birthday = "",
            occupation = emptyList(),
            status = "status",
            nickname = "nickname",
            appearance = emptyList(),
            portrayed = "portrayed",
            category = "category",
            betterCallSaulAppearance = emptyList()
        )

        private val fakeCharacter2 = Character(
            id = 133,
            name = "Second Fake Character",
            imageUrl = "image_url2",
            birthday = "",
            occupation = emptyList(),
            status = "status",
            nickname = "nickname",
            appearance = emptyList(),
            portrayed = "portrayed",
            category = "category",
            betterCallSaulAppearance = emptyList()
        )

        private val fakeCharacterList = listOf(fakeCharacter1, fakeCharacter2)
    }
}
