package com.techtask.breakingbadcharacters.presentation.characterdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techtask.breakingbadcharacters.common.viewmodel.BaseViewModel
import com.techtask.breakingbadcharacters.domain.usecase.GetCharacterByIdUseCase
import com.techtask.breakingbadcharacters.domain.result.Result
import com.techtask.common.CoroutineDispatcherProvider
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
): BaseViewModel(coroutineDispatcherProvider) {

    private val _details = MutableLiveData<CharacterDetailsUIModel>()
    val details: LiveData<CharacterDetailsUIModel> = _details

    fun loadData(characterId: Int) {
        launchOnMain {
            getCharacterByIdUseCase.execute(characterId).apply {
                if (this is Result.Success) {
                    _details.value = CharacterDetailsUIModel(
                        name = data.name,
                        imageUrl = data.imageUrl,
                        birthday = data.birthday,
                        occupation = data.occupation,
                        status = data.status,
                        nickname = data.nickname,
                        appearance = data.appearance,
                        portrayed = data.portrayed,
                        category = data.category,
                        betterCallSaulAppearance = data.betterCallSaulAppearance
                    )
                } else {
                    Log.e("BreakingBad", "Couldn't find a character with id: $characterId")
                }
            }
        }
    }
}
