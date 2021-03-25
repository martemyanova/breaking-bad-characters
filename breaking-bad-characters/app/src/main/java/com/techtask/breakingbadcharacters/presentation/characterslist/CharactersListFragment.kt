package com.techtask.breakingbadcharacters.presentation.characterslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.BaseFragment
import com.techtask.breakingbadcharacters.common.viewmodel.ViewModelFactory
import com.techtask.breakingbadcharacters.presentation.characterdetails.CharacterDetailsFragment
import com.techtask.breakingbadcharacters.presentation.characterslist.ui.CharactersListUIComponent
import com.techtask.breakingbadcharacters.presentation.characterslist.viewmodel.CharacterListViewModel
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uiComponent = CharactersListUIComponent(::onCharacterSelected, ::onReloadRequested)
        return uiComponent.inflate(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiComponent.onViewCreated()

        with (viewModel) {
            characters.observe(viewLifecycleOwner) { data ->
                data?.let { uiComponent.bindData(it) }
            }
            state.observe(viewLifecycleOwner) { state ->
                state?.let { uiComponent.updateState(it) }
            }
            load()
        }
    }

    private fun onCharacterSelected(characterId: Int) {
        view?.let {
            findNavController().navigate(
                R.id.action_charactersListFragment_to_characterDetailsFragment,
                CharacterDetailsFragment.Arguments(characterId).toBundle())
        }
    }

    private fun onReloadRequested() {
        viewModel.load()
    }

    override fun onDestroyView() {
        with (viewModel) {
            characters.removeObservers(viewLifecycleOwner)
            state.removeObservers(viewLifecycleOwner)
        }
        super.onDestroyView()
    }
}
