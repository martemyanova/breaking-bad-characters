package com.techtask.breakingbadcharacters.presentation.characterslist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.BaseFragment
import com.techtask.breakingbadcharacters.domain.result.Result
import com.techtask.breakingbadcharacters.domain.usecase.GetAllCharactersUseCase
import com.techtask.breakingbadcharacters.presentation.characterdetails.CharacterDetailsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class CharactersListFragment : BaseFragment() {

    @Inject
    lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.iv_image).setOnClickListener {
            view.findNavController().navigate(
                R.id.action_charactersListFragment_to_characterDetailsFragment,
                CharacterDetailsFragment.Arguments(1).toBundle())
        }

        lifecycleScope.launch(Dispatchers.IO) {
            getAllCharactersUseCase.execute().apply {
                when (this) {
                    is Result.Success -> {
                        val data = this.data[0]

                        val url = URL(data.imageUrl)
                        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                        updateContent(view, data.name, bmp)
                    }
                    is Result.Failure -> {
                        updateContent(view, "Failed to retrieve data! ${this.exception}")
                    }
                }
            }
        }
    }

    private suspend fun updateContent(view: View, data: String, bmp: Bitmap? = null) {
        withContext(Dispatchers.Main) {
            view.findViewById<TextView>(R.id.tv_content).text = data
            bmp?.let {
                view.findViewById<ImageView>(R.id.iv_image).setImageBitmap(bmp)
            }
        }
    }
}
