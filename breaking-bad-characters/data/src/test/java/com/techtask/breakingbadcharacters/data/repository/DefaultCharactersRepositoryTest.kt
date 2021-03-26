package com.techtask.breakingbadcharacters.data.repository

import com.techtask.breakingbadcharacters.data.cache.LocalDataSource
import com.techtask.breakingbadcharacters.data.remote.datasource.RemoteDataSource
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class DefaultCharactersRepositoryTest {

    @InjectMocks
    private lateinit var SUT: DefaultCharactersRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    @Mock
    private lateinit var localDataSource: LocalDataSource


    @Test
    fun `GIVEN no data cached in local data source WHEN executed THEN remote data source method called`() = runBlockingTest {
        //given

        //when
        SUT.getAllCharacters()

        //then
        verify(remoteDataSource, times(1)).getAllCharacters()
    }

    @Test
    fun `GIVEN no data cached in local data source WHEN executed THEN remote data returned`() = runBlockingTest {
        //given
        `when`(remoteDataSource.getAllCharacters())
            .thenReturn(Result.Success(listOf(fakeCharacter)))

        //when
        val actual = (SUT.getAllCharacters() as Result.Success).data

        //then
        assertEquals(1, actual.size)
        assertEquals(fakeCharacter, actual[0])
    }

    @Test
    fun `GIVEN data cached in local data source WHEN executed THEN no remote data source method called`() = runBlockingTest {
        //given
        `when`(localDataSource.getAllCharacters()).thenReturn(listOf(fakeCharacter))

        //when
        SUT.getAllCharacters()

        //then
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun `GIVEN data cached in local data source WHEN executed THEN cached data returned`() = runBlockingTest {
        //given
        `when`(localDataSource.getAllCharacters()).thenReturn(listOf(fakeCharacter))

        //when
        val actual = (SUT.getAllCharacters() as Result.Success).data

        //then
        assertEquals(1, actual.size)
        assertEquals(fakeCharacter, actual[0])
    }

    companion object {
        private val fakeCharacter = Character(
            id = 1,
            name = "Fake Character",
            imageUrl = "image_url",
            birthday = "",
            occupation = emptyList(),
            status = "status",
            nickname = "nickname",
            appearance = emptyList(),
            portrayed = "portrayed",
            category = "category",
            betterCallSaulAppearance = emptyList()
        )
    }
}
