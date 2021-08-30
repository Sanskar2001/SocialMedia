package com.san22.socialmedia.ui.feed

import android.app.LauncherActivity
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.san22.libimgur.models.GalleryResponse
import com.san22.socialmedia.R
import com.san22.socialmedia.databinding.ListItemBinding
import kotlinx.coroutines.android.awaitFrame

class FeedRecyclerAdapter() :
    ListAdapter<GalleryResponse.Data, FeedRecyclerAdapter.FeedViewHolder>(FeedDiffCallBack()) {

    class FeedViewHolder (val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    class FeedDiffCallBack: DiffUtil.ItemCallback<GalleryResponse.Data>()
    {
        override fun areItemsTheSame(oldItem: GalleryResponse.Data, newItem: GalleryResponse.Data): Boolean {
          return oldItem===newItem
        }

        override fun areContentsTheSame(
            oldItem: GalleryResponse.Data,
            newItem: GalleryResponse.Data
        ): Boolean {
           return oldItem.toString().equals(newItem.toString())
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {

        val inflater=parent.context.getSystemService(LayoutInflater::class.java)
        val binding=ListItemBinding.inflate(inflater,parent,false)

        return FeedViewHolder(binding )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val image=getItem(position)
        holder.binding.captionTextView.text= image.title.toString()

        //Removing Viral icon for all post
        holder.binding.viralIcon.isVisible=false
        holder.binding.viralTextView.isVisible=false


        if (image.description.toString().isNullOrBlank())
        holder.binding.descTextView.text=image.description.toString()

        holder.binding.accTextView.text=image.accountUrl

        holder.binding.likeCountTextView.text=image.favoriteCount.toString()
        holder.binding.commentCountTextView.text=image.commentCount.toString()

        if (image.inMostViral==true)
        {
            holder.binding.viralIcon.isVisible=true
            holder.binding.viralTextView.isVisible=true
        }


        holder.binding.imageView.load("https://i.imgur.com/${image.cover}.jpg")
        {
            
            placeholder(R.drawable.loading)
            error(R.drawable.notavailable)
        }
    }
}