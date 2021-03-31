package com.techtask.breakingbadcharacters.presentation.characterdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.viewmodel.BaseViewModel
import com.techtask.breakingbadcharacters.domain.usecase.GetCharacterByIdUseCase
import com.techtask.breakingbadcharacters.domain.result.Result
import com.techtask.breakingbadcharacters.presentation.characterdetails.ui.CharacterDetailUIModel
import com.techtask.breakingbadcharacters.presentation.characterdetails.ui.CharacterDetailsUIModel
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
                    val details = ArrayList<CharacterDetailUIModel>().apply {
                        add(CharacterDetailUIModel(titleResId = R.string.details_title_name, text = data.name))
                        add(CharacterDetailUIModel(titleResId = R.string.details_title_bithday, text = data.birthday))
                        add(CharacterDetailUIModel(titleResId = R.string.details_title_bithday, text = data.status))
                        add(CharacterDetailUIModel(titleResId = R.string.details_title_occupation, text = data.occupation.toText()))
                        add(CharacterDetailUIModel(titleResId = R.string.details_title_appearance, text = data.appearance.map { it.toString() }.toText()))
                    }

                    _details.value = CharacterDetailsUIModel(
                        imageUrl = data.imageUrl,
                        details = details
                    )
                } else {
                    Log.e("BreakingBad", "Couldn't find a character with id: $characterId")
                }
            }
        }
    }

    private fun List<String>.toText(): String {
        val stringBuffer = StringBuffer()
        this.forEachIndexed { index, item ->
            if (index != 0) {
                stringBuffer.append(", ")
            }
            stringBuffer.append(item)
        }
        return stringBuffer.toString()
    }
}
