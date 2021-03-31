package com.techtask.breakingbadcharacters.presentation.characterdetails.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.imageloader.load
import com.techtask.breakingbadcharacters.presentation.characterslist.ui.adapter.CharactersListAdapter

class CharacterDetailsUIComponent {

    private lateinit var characterDetailsAdapter: CharacterDetailsAdapter
    private val layoutId = R.layout.screen_character_details

    private lateinit var rootView: View

    val toolbar: Toolbar get() = rootView.findViewById(R.id.toolbar)

    fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }

    fun onViewCreated() {
        detailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            characterDetailsAdapter = CharacterDetailsAdapter()
            adapter = characterDetailsAdapter
        }
    }

    fun bindData(data: CharacterDetailsUIModel) {
        imageView.load(data.imageUrl)
        characterDetailsAdapter.submitData(data.details)
    }

    private val imageView: ImageView by lazy { rootView.findViewById(R.id.iv_image) }
    private val detailsRecyclerView: RecyclerView by lazy { rootView.findViewById(R.id.rv_character_details) }
}