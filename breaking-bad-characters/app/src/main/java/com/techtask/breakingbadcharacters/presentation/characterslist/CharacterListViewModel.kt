package com.techtask.breakingbadcharacters.presentation.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techtask.breakingbadcharacters.common.viewmodel.BaseViewModel
import com.techtask.breakingbadcharacters.domain.result.Result
import com.techtask.breakingbadcharacters.domain.usecase.GetAllCharactersUseCase
import com.techtask.breakingbadcharacters.presentation.characterslist.ui.CharacterUIModel
import com.techtask.common.CoroutineDispatcherProvider
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : BaseViewModel(coroutineDispatcherProvider) {

    private val _characters = MutableLiveData<List<CharacterUIModel>>()
    val characters: LiveData<List<CharacterUIModel>> = _characters

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun load() {
        _state.value = State.LOADING
        launchOnMain {
            getAllCharactersUseCase.execute().apply {
                when (this) {
                    is Result.Success -> {
                        _characters.value = data.map {
                            CharacterUIModel(
                                id = it.id,
                                name = it.name,
                                imageUrl = it.imageUrl
                            )
                        }
                        _state.value = State.DATA_READY
                    }
                    is Result.Failure -> {
                        _state.value = State.FAILURE
                    }
                }
            }
        }
    }

    enum class State { LOADING, DATA_READY, FAILURE }

}
