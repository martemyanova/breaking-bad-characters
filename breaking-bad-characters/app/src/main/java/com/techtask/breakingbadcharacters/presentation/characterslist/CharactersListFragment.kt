package com.techtask.breakingbadcharacters.presentation.characterslist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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

        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)
            .get(CharacterListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uiComponent = CharactersListUIComponent(::onCharacterSelected, ::onReloadRequested)
        val view = uiComponent.inflate(inflater, container)
        setupSearchView()
        return view
    }

    private fun setupSearchView() {
        uiComponent.toolbar.apply {
            inflateMenu(R.menu.menu_main)

            val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
            (menu.findItem(R.id.menu_search)?.actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                isIconifiedByDefault = false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController?.let {
            NavigationUI.setupWithNavController(uiComponent.toolbar, it)
        }

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
