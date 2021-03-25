package com.techtask.breakingbadcharacters.data.remote

import com.techtask.breakingbadcharacters.data.remote.api.RemoteSourceApi
import com.techtask.breakingbadcharacters.data.util.TestFileUtils
import com.techtask.breakingbadcharacters.data.util.testingCoroutineDispatcherProvider
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HttpsURLConnection
import com.techtask.breakingbadcharacters.domain.result.Result
import org.junit.jupiter.api.Assertions.*

internal class DefaultRemoteDataSourceTest {

    private lateinit var SUT: DefaultRemoteDataSource

    private lateinit var remoteSourceApi: RemoteSourceApi
    private val mockWebServer = MockWebServer()

    @BeforeEach
    fun setUp() {
        mockWebServer.start()

        remoteSourceApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteSourceApi::class.java)

        SUT = DefaultRemoteDataSource(remoteSourceApi, testingCoroutineDispatcherProvider)
    }

    @Test
    fun `GIVEN valid remote response returned WHEN executed THEN success result returned`() = runBlocking {
        //given
        val mockedResponse = MockResponse()
            .setResponseCode(HttpsURLConnection.HTTP_OK)
            .setBody(TestFileUtils.readFromFile(
                fileName = TestFileUtils.Files.RESPONSE_CHARACTERS_LIST
            ))
        mockWebServer.enqueue(mockedResponse)

        //when
        val actual = SUT.getAllCharacters()

        //then
        assertTrue(actual is Result.Success)
    }

    @Test
    fun `GIVEN valid remote response returned WHEN executed THEN valid data returned`() = runBlocking {
        //given
        val mockedResponse = MockResponse()
            .setResponseCode(HttpsURLConnection.HTTP_OK)
            .setBody(TestFileUtils.readFromFile(
                fileName = TestFileUtils.Files.RESPONSE_CHARACTERS_LIST
            ))
        mockWebServer.enqueue(mockedResponse)

        //when
        val actual = (SUT.getAllCharacters() as Result.Success).data
        val actualData = actual[0]

        //then
        assertAll(
            { assertEquals(1, actual.size) },
            { assertEquals(1, actualData.id) },
            { assertEquals("Walter White", actualData.name) },
            { assertEquals("09-07-1958", actualData.birthday) },
            { assertArrayEquals(
                arrayOf("High School Chemistry Teacher", "Meth King Pin"),
                actualData.occupation.toTypedArray()) },
            { assertEquals(
                "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
                actualData.imageUrl) }
        )
    }

    @Test
    fun `GIVEN invalid remote response returned WHEN executed THEN failure result returned`() = runBlocking {
        //given
        val mockedResponse = MockResponse()
            .setResponseCode(HttpsURLConnection.HTTP_OK)
            .setBody(TestFileUtils.readFromFile(
                fileName = TestFileUtils.Files.RESPONSE_CHARACTERS_LIST_INVALID
            ))
        mockWebServer.enqueue(mockedResponse)

        //when
        val actual = SUT.getAllCharacters()

        //then
        assertTrue(actual is Result.Failure)
    }

    @Test
    fun `GIVEN error remote response returned WHEN executed THEN failure result returned`() = runBlocking {
        //given
        val mockedResponse = MockResponse()
            .setResponseCode(HttpsURLConnection.HTTP_INTERNAL_ERROR)
            .setBody(TestFileUtils.readFromFile(
                fileName = TestFileUtils.Files.RESPONSE_CHARACTERS_LIST
            ))
        mockWebServer.enqueue(mockedResponse)

        //when
        val actual = SUT.getAllCharacters()

        //then
        assertTrue(actual is Result.Failure)
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
