package com.techtask.breakingbadcharacters.presentation.characterslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
    ): View? {
        return uiComponent.inflate(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.iv_image).setOnClickListener {
            view.findNavController().navigate(
                R.id.action_charactersListFragment_to_characterDetailsFragment,
                CharacterDetailsFragment.Arguments(1).toBundle())
        }

        with (viewModel) {
            characters.observe(viewLifecycleOwner) { data ->
                data?.let { uiComponent.bindData(it) }
            }
            state.observe(viewLifecycleOwner) { state ->
                when (state) {
//                    State.LOADING -> TODO()
//                    State.DATA_READY -> TODO()
//                    State.FAILURE -> TODO()
                }
            }
            load()
        }
    }
}
