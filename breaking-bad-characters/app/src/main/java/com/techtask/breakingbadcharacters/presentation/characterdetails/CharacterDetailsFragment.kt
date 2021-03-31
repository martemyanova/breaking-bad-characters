package com.techtask.breakingbadcharacters.presentation.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.NavigationUI
import com.techtask.breakingbadcharacters.common.BaseFragment
import com.techtask.breakingbadcharacters.common.viewmodel.ViewModelFactory
import javax.inject.Inject

class CharacterDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: CharacterDetailsViewModel

    private lateinit var uiComponent: CharacterDetailsUIComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)
            .get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uiComponent = CharacterDetailsUIComponent()
        return uiComponent.inflate(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController?.let {
            NavigationUI.setupWithNavController(uiComponent.toolbar, it)
        }

        val characterId = Arguments.fromBundle(arguments).characterId

        with (viewModel) {
            details.observe(viewLifecycleOwner) { details ->
                details?.let { uiComponent.bindData(it) }
            }
            loadData(characterId)
        }
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
