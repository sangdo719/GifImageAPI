package com.example.anniegif.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anniegif.R
import com.example.anniegif.databinding.ActivityGifItemBinding
import com.example.anniegif.model.GifInfo
import com.example.anniegif.view.FullScreenGifActivity

class AnnieGifAdapter
    : RecyclerView.Adapter<AnnieGifAdapter.AnnieGifViewHolder>() {
    private val gifInfoList = mutableListOf<GifInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnieGifViewHolder {
        return AnnieGifViewHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: AnnieGifViewHolder, position: Int) {
        holder.loadInfo(gifInfoList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, FullScreenGifActivity::class.java)
            intent.putExtra("gifInfoUrl", gifInfoList[position].url)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return gifInfoList.size
    }

    fun updateGifInfoList(newList: List<GifInfo>){
        val size = this.gifInfoList.size

        this.gifInfoList.clear()
        notifyItemRangeRemoved(0, size)

        this.gifInfoList.addAll(newList)
        notifyItemRangeInserted(0, newList.size)
    }

    class AnnieGifViewHolder(private val binding: ActivityGifItemBinding)
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
                    val binding = ActivityGifItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false)
                    return AnnieGifViewHolder(binding)
                }
            }
        }

}