package com.techtask.breakingbadcharacters.data.util

object TestFileUtils {

    object Files {
        const val RESPONSE_CHARACTERS_LIST = "remote_response_success.json"
        const val RESPONSE_CHARACTERS_LIST_INVALID = "remote_response_invalid.json"
    }

    fun readFromFile(fileName: String): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return fileInputStream?.bufferedReader()?.readText().orEmpty()
    }
}
