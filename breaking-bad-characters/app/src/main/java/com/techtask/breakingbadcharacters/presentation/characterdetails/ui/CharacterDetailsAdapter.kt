package com.techtask.breakingbadcharacters.presentation.characterdetails.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techtask.breakingbadcharacters.R

class CharacterDetailsAdapter() : RecyclerView.Adapter<CharacterDetailsAdapter.CharacterViewHolder>() {

    private var characterDetails: List<CharacterDetailUIModel> = emptyList()
    private val itemLayoutId = R.layout.list_item_character_details

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayoutId, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        Log.d("BreakingBad", "submitData, position: ${position}")
        val data = characterDetails[position]
        with (holder) {
            titleTextView.setText(data.titleResId)
            textTextView.text = data.text
        }
    }

    override fun getItemCount() = characterDetails.size

    fun submitData(data: List<CharacterDetailUIModel>) {
        characterDetails = data
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val textTextView: TextView = itemView.findViewById(R.id.tv_text)
    }
}
