package com.techtask.breakingbadcharacters.presentation.characterslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techtask.breakingbadcharacters.common.viewmodel.BaseViewModel
import com.techtask.breakingbadcharacters.domain.model.Character
import com.techtask.breakingbadcharacters.domain.result.Result
import com.techtask.breakingbadcharacters.domain.usecase.GetAllCharactersUseCase
import com.techtask.breakingbadcharacters.domain.usecase.SearchCharactersByNameUseCase
import com.techtask.common.CoroutineDispatcherProvider
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val searchCharactersByNameUseCase: SearchCharactersByNameUseCase
) : BaseViewModel(coroutineDispatcherProvider) {

    private val _characters = MutableLiveData<List<CharacterUIModel>>()
    val characters: LiveData<List<CharacterUIModel>> = _characters

    private val _state = MutableLiveData<CharacterListViewState>()
    val state: LiveData<CharacterListViewState> = _state

    private var searchString: String = ""

    fun load() {
        if (searchString.isEmpty()) {
            loadAllResult()
        } else {
            loadSearchResult(searchString)
        }
    }

    fun onSearchRequest(searchString: String) {
        this.searchString = searchString
        _state.value = CharacterListViewState.LOADING
        load()
    }

    private fun loadAllResult() {
        _state.value = CharacterListViewState.LOADING
        launchOnMain {
            getAllCharactersUseCase.execute().apply {
                when (this) {
                    is Result.Success -> {
                        _characters.value = data.map { it.toUIModel() }
                        _state.value = CharacterListViewState.DATA_READY
                    }
                    is Result.Failure -> {
                        _state.value = CharacterListViewState.FAILURE
                    }
                }
            }
        }
    }

    private fun loadSearchResult(searchString: String) {
        _state.value = CharacterListViewState.LOADING
        launchOnMain {
            _characters.value = searchCharactersByNameUseCase.execute(searchString)
                .map { it.toUIModel() }
                .also {
                    _state.value = if (it.isEmpty()) {
                        CharacterListViewState.NOTHING_WAS_FOUND
                    } else {
                        CharacterListViewState.DATA_READY
                    }
                }
        }
    }

    private fun Character.toUIModel() =
        CharacterUIModel(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl
        )

}
