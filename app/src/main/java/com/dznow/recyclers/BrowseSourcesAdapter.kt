package com.dznow.recyclers

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.models.SourceModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_browse_source.view.*

class BrowseSourcesAdapter(private val sources: List<SourceModel>) :
    RecyclerView.Adapter<BrowseSourcesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.layout_browse_source, null)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = sources[position]
        holder.targetName.text = source.name
        Picasso.get().load(source.logo_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop(Gravity.START)
            .into(holder.sourceCover)
    }

    override fun getItemCount() = sources.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var targetName: TextView
        var sourceCover: ImageView

        init {
            itemView.setOnClickListener(this)
            targetName = itemView.target_name
            sourceCover = itemView.tv_item_cover
        }

        override fun onClick(view: View) {
            Toast.makeText(
                view.context,
                "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}
