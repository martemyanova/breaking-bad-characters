package com.techtask.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

data class CoroutineDispatcherProvider(
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val unconfined: CoroutineDispatcher
) {

    @Inject
    constructor() : this(
        Dispatchers.Main,
        Dispatchers.Default,
        Dispatchers.IO,
        Dispatchers.Unconfined
    )
}
