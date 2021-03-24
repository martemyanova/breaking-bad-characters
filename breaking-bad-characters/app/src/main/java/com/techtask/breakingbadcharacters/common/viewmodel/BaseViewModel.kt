package com.techtask.breakingbadcharacters.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtask.common.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel(
    protected val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    protected fun launchOnIO(block: suspend (CoroutineScope) -> Unit) {
        viewModelScope.launch(coroutineDispatcherProvider.io) { block(this) }
    }

    protected fun launchOnMain(block: suspend () -> Unit) {
        viewModelScope.launch(coroutineDispatcherProvider.main) { block() }
    }
}
