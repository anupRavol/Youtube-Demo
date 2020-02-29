package com.example.youtubedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubedemo.data.Item
import com.example.youtubedemo.utils.PicassoCircleTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popular_list_item.view.*

class PopularListAdapter(private val listener: PopularListEvents) : RecyclerView.Adapter<PopularListAdapter.ViewHolder>(){

    private var popularVideos :List<Item> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = popularVideos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(popularVideos[position], listener)
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun bind(item : Item, listener: PopularListEvents){
            itemView.title.text = item.snippet?.title
            itemView.description.text = item.snippet?.channelTitle
            itemView.setOnClickListener { listener.onPostClicked(item) }
            Picasso.get()
                .load(item.snippet?.thumbnails?.high?.url)
                .placeholder(android.R.drawable.gallery_thumb)
                .error(android.R.drawable.gallery_thumb)
                .fit()
                .centerCrop()
                .into(itemView.thumbnail)
        }
    }

    fun setPopularVideos(popularVideos : List<Item>){
        this.popularVideos = popularVideos
        notifyDataSetChanged()
    }

    interface PopularListEvents{
        fun onPostClicked(item: Item)
    }

}