package com.techtask.breakingbadcharacters.presentation.characterslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.imageloader.load

class CharactersListAdapter : RecyclerView.Adapter<CharactersListAdapter.CharacterViewHolder>() {

    private var charactersList: List<CharacterUIModel> = emptyList()
    private val itemLayoutId = R.layout.list_item_characters_list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayoutId, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = charactersList[position]
        with (holder) {
            nameTextView.text = character.name
            imageView.load(character.imageUrl)
        }
    }

    override fun getItemCount() = charactersList.size

    fun submitData(data: List<CharacterUIModel>) {
        charactersList = data
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_image)
        val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
    }
}
