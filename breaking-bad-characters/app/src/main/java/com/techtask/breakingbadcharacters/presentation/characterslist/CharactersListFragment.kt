package com.techtask.breakingbadcharacters.presentation.characterslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.BaseFragment
import com.techtask.breakingbadcharacters.common.viewmodel.ViewModelFactory
import com.techtask.breakingbadcharacters.presentation.characterdetails.CharacterDetailsFragment
import com.techtask.breakingbadcharacters.presentation.characterslist.CharacterListViewModel.State
import com.techtask.breakingbadcharacters.presentation.characterslist.ui.CharactersListUIComponent
import javax.inject.Inject

class CharactersListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: CharacterListViewModel

    private lateinit var uiComponent: CharactersListUIComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)
            .get(CharacterListViewModel::class.java)

        uiComponent = CharactersListUIComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return uiComponent.inflate(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiComponent.onViewCreated()
        Log.d("BreakingBad", "onViewCreated")

        with (viewModel) {
            characters.observe(viewLifecycleOwner) { data ->
                Log.d("BreakingBad", "onViewCreated, data: ${data.size}")
                data?.let { uiComponent.bindData(it) }
            }
            state.observe(viewLifecycleOwner) { state ->
                when (state) {
                    State.LOADING -> Log.d("BreakingBad", "LOADING")
                    State.DATA_READY -> Log.d("BreakingBad", "DATA_READY")
                    State.FAILURE -> Log.d("BreakingBad", "FAILURE")
                }
            }
            load()
        }
    }

    private fun onCharacterSelected(characterId: Int = 1) {
        view?.let {
            findNavController().navigate(
                R.id.action_charactersListFragment_to_characterDetailsFragment,
                CharacterDetailsFragment.Arguments(characterId).toBundle())
        }
    }
}
