package com.techtask.breakingbadcharacters.presentation.characterslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.presentation.characterslist.ui.adapter.CharactersListAdapter
import com.techtask.breakingbadcharacters.presentation.characterslist.viewmodel.CharacterListViewState
import com.techtask.breakingbadcharacters.presentation.characterslist.viewmodel.CharacterListViewState.*
import com.techtask.breakingbadcharacters.presentation.characterslist.viewmodel.CharacterUIModel

class CharactersListUIComponent(
    private val onCharacterSelected: (characterId: Int) -> Unit,
    private val onReloadClicked: () -> Unit
) {
    private val layoutId = R.layout.screen_characters_list

    private lateinit var rootView: View

    private lateinit var charactersListAdapter: CharactersListAdapter

    val toolbar: Toolbar get() = rootView.findViewById(R.id.toolbar)

    fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }

    fun onViewCreated() {
        setupRecyclerView()
        setupCTA()
    }

    fun bindData(data: List<CharacterUIModel>) {
        charactersListAdapter.submitData(data)
    }

    fun updateState(state: CharacterListViewState) {
        contentRecyclerView.isVisible = state == DATA_READY
        progressBarView.isVisible = state == LOADING
        errorMessageTextView.isVisible = state == FAILURE
        reloadButton.isVisible = state == FAILURE
        nothingFoundMessageTextView.isVisible = state == NOTHING_WAS_FOUND
    }

    private fun setupRecyclerView() {
        contentRecyclerView.apply {
            layoutManager = GridLayoutManager(context, numberOfColumns)
            charactersListAdapter = CharactersListAdapter(onCharacterSelected)
            adapter = charactersListAdapter
        }
    }

    private fun setupCTA() {
        reloadButton.setOnClickListener {
            onReloadClicked()
        }
    }

    private val context by lazy { rootView.context }
    private val numberOfColumns by lazy { context.resources.getInteger(
        R.integer.character_list_screen_number_of_columns) }

    private val contentRecyclerView by lazy {
        rootView.findViewById<RecyclerView>(R.id.rv_characters) }
    private val progressBarView by lazy {
        rootView.findViewById<ProgressBar>(R.id.pb_progress_bar) }
    private val errorMessageTextView by lazy {
        rootView.findViewById<TextView>(R.id.tv_error_message) }
    private val reloadButton by lazy {
        rootView.findViewById<Button>(R.id.bt_reload) }
    private val nothingFoundMessageTextView by lazy {
        rootView.findViewById<TextView>(R.id.tv_nothing_was_found_message) }
}
