package com.techtask.breakingbadcharacters.presentation.characterslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.techtask.breakingbadcharacters.R

class CharactersListUIComponent {

    private val layoutId = R.layout.screen_characters_list

    private lateinit var rootView: View

    fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }

    fun bindData(data: List<CharacterUIModel>) {
        rootView.findViewById<TextView>(R.id.tv_content).text = data.get(0).name
    }
}
