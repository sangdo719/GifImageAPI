package com.example.anniegif.view

import android.R.layout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.anniegif.adapter.AnnieGifAdapter
import com.example.anniegif.adapter.CategoryAdapter
import com.example.anniegif.databinding.FragmentCategoryBinding
import com.example.anniegif.model.Category
import com.example.anniegif.model.GifInfo
import com.example.anniegif.viewmodel.GifViewModel

class CategoryFragment : Fragment() {
    private val gifVM by activityViewModels<GifViewModel>()
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCategoryBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinner(view)
        onSubmitBtnClick()
        observeObservables()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSubmitBtnClick() = with(binding) {
        btnSubmit.setOnClickListener {
            if (etAmount.text.toString() != ""){
                gifVM.fetchData((spCategory.selectedItem as Category).title, etAmount.text.toString().toInt())
            }else{
                gifVM.fetchData((spCategory.selectedItem as Category).title,0)
            }
        }
    }

    private fun setSpinner(view: View) = with(gifVM) {
        categories.observe(viewLifecycleOwner) { categoryList ->
            binding.spCategory.adapter = CategoryAdapter(view.context, layout.simple_list_item_1, categoryList)
        }
    }

    private fun observeObservables() = with(gifVM) {
        gifModelObj.observe(viewLifecycleOwner){ gifList ->
            binding.rvGif.adapter = AnnieGifAdapter(gifList, ::itemClicked)
        }
    }

    private fun itemClicked(item: GifInfo) = with(findNavController()){
        val action = CategoryFragmentDirections.goToFullScreenGifFragment(item)
        navigate(action)
    }
}
