package com.techtask.breakingbadcharacters.presentation.characterdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.BaseFragment

class CharacterDetailsFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        val characterId = Arguments.fromBundle(arguments).characterId
        Log.d("BreakingBad", "characterId: $characterId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_character_details, container, false)
    }

    data class Arguments(val characterId: Int) {
        fun toBundle() = Bundle().apply {
            putInt(EXTRA_CHARACTER_ID, characterId)
        }

        companion object {
            private const val EXTRA_CHARACTER_ID = "characterId"

            fun fromBundle(arguments: Bundle?): Arguments {
                val characterId = arguments?.getInt(EXTRA_CHARACTER_ID) ?: throw IllegalStateException(
                    "Character id is not found, please make sure the required extras are passed.")
                return Arguments(characterId)
            }
        }
    }
}
