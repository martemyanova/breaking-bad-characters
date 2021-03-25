package com.techtask.breakingbadcharacters.data.util

import com.techtask.common.CoroutineDispatcherProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher

val testingCoroutineDispatcherProvider: CoroutineDispatcherProvider
    get() {
        val dispatcher = TestCoroutineDispatcher()
        return CoroutineDispatcherProvider(
            main = dispatcher,
            computation = dispatcher,
            io = dispatcher,
            unconfined = dispatcher
        )
    }
