package com.example.anniegif.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anniegif.R
import com.example.anniegif.databinding.GifItemBinding
import com.example.anniegif.model.GifInfo

class AnnieGifAdapter(
    private val gifInfoList: List<GifInfo>,
    private val gifSelected: (GifInfo) -> Unit
) : RecyclerView.Adapter<AnnieGifAdapter.AnnieGifViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = AnnieGifViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: AnnieGifViewHolder, position: Int) {
        gifInfoList[position].let { item ->
            holder.loadInfo(item)
            holder.itemView.setOnClickListener { gifSelected(item) }
        }
    }

    override fun getItemCount() = gifInfoList.size

    class AnnieGifViewHolder(private val binding: GifItemBinding)
        :RecyclerView.ViewHolder(binding.root) {

            fun loadInfo(item: GifInfo) = with(binding) {
                txtView.text = item.anime_name
                if (item.url != null){
                    imgView.loadGif(item.url)
                }else{
                    imgView.setImageResource(R.mipmap.ic_launcher)
                }
            }

            private fun ImageView.loadGif(url: String){
                Glide.with(this).asGif().load(url).into(this)
            }

            companion object{
                fun getInstance(parent: ViewGroup): AnnieGifViewHolder{
                    val binding = GifItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false)
                    return AnnieGifViewHolder(binding)
                }
            }
        }

}