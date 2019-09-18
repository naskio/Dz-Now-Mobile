package com.dznow.recyclers

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.dznow.R
import com.dznow.models.VideoModel
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.layout_video.view.*

class VideoPreviewAdapter(private val videos: ArrayList<VideoModel>) :
    RecyclerView.Adapter<VideoPreviewAdapter.VideoPreviewHolder>() {

    // creating the layout for the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPreviewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_video, parent, false)
        return VideoPreviewHolder(view)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    // passing data to the view Holder
    override fun onBindViewHolder(holder: VideoPreviewHolder, position: Int) {
        val video = videos[position]
        holder.titleView.text = video.title
        Picasso.get().load(video.cover)
            .placeholder(R.drawable.placeholder_gray)
            .fit()
            .centerCrop()
            .into(holder.coverView)
        holder.video = video
    }

    private fun buttonBookmarkSetImage(buttonBookmark: ImageButton, checked: Boolean) {
        if (checked) {
            buttonBookmark.setImageResource(R.drawable.ic_outline_bookmark_24px)
        } else {
            buttonBookmark.setImageResource(R.drawable.ic_outline_bookmark_border_24px)
        }
    }

    // Article Preview Holder
    inner class VideoPreviewHolder(itemView: View, var video: VideoModel? = null) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val titleView: TextView
        val coverView: ImageView

        init {
            itemView.setOnClickListener(this)
            titleView = itemView.title
            coverView = itemView.cover_url
        }

        override fun onClick(view: View) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video?.url))
            intent.putExtra("url", video?.url)
            intent.putExtra("title", video?.title)
            intent.putExtra("cover", video?.cover)
            view.context.startActivity(intent)
        }
    }
}
