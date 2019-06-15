package com.dznow.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.category.CategoryModel

class CategoryAdapter(private val categories: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.layout_browse_item, null)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.targetName.text = categories[position].name
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var targetName: TextView

        init {
            itemView.setOnClickListener(this)
            targetName = itemView.findViewById<View>(R.id.target_name) as TextView
        }

        override fun onClick(view: View) {
            Toast.makeText(view.context,
                "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
