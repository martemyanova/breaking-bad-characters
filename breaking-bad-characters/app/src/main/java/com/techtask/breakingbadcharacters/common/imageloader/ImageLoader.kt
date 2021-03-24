package com.techtask.breakingbadcharacters.common.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}
