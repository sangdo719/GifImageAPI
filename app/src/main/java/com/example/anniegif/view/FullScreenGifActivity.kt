package com.example.anniegif.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.anniegif.databinding.ActivityFullScreenGifBinding
import com.example.anniegif.model.GifInfo
import com.google.android.material.imageview.ShapeableImageView

class FullScreenGifActivity: AppCompatActivity() {
    private val binding by lazy {ActivityFullScreenGifBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.extras?.get("gifInfoUrl") as String
        Glide.with(this).asGif().load(url).into(binding.fullScreenGif)

        binding.btnBack.setOnClickListener(){
            finish()
        }

    }
}