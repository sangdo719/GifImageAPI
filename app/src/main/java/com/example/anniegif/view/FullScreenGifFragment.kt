package com.example.anniegif.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.anniegif.databinding.FragmentFullScreenGifBinding

class FullScreenGifFragment: Fragment() {
    private var _binding: FragmentFullScreenGifBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<FullScreenGifFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFullScreenGifBinding.inflate(
        inflater, container, false
    ).also{ _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).asGif().load(args.item.url).into(binding.fullScreenGif)
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}