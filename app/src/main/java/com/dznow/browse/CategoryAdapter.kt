package com.dznow.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.category.CategoryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_browse_item.view.*

class CategoryAdapter(private val categories: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.layout_browse_item, null)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.targetName.text = category.name
        Picasso.get().load(category.background_url).into(holder.categoryCover)
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var targetName: TextView
        var categoryCover : ImageView

        init {
            itemView.setOnClickListener(this)
            targetName = itemView.target_name
            categoryCover = itemView.tv_item_cover
        }

        override fun onClick(view: View) {
            Toast.makeText(view.context,
                "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
