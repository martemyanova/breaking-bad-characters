package com.techtask.breakingbadcharacters.presentation.characterslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techtask.breakingbadcharacters.R

class CharactersListUIComponent(
    private val onCharacterSelected: (characterId: Int) -> Unit
) {
    private val layoutId = R.layout.screen_characters_list

    private lateinit var rootView: View

    private lateinit var charactersListAdapter: CharactersListAdapter

    fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }

    fun onViewCreated() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        contentRecyclerView.apply {
            layoutManager = GridLayoutManager(context, numberOfColumns)
            charactersListAdapter = CharactersListAdapter(onCharacterSelected)
            adapter = charactersListAdapter
        }
    }

    fun bindData(data: List<CharacterUIModel>) {
        charactersListAdapter.submitData(data)
    }

    private val context by lazy { rootView.context }
    private val numberOfColumns by lazy { context.resources.getInteger(
        R.integer.character_list_screen_number_of_columns) }

    private val contentRecyclerView by lazy {
        rootView.findViewById<RecyclerView>(R.id.rv_characters)
    }
}
