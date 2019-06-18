package com.dznow.recyclers

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.models.CategoryModel
import kotlinx.android.synthetic.main.layout_category_preview.view.*

class CategoryPreviewAdapter(private val categories: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryPreviewAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_preview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.textView.text = category.name
        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayout.VERTICAL, false)
            adapter = category.articles?.let { ArticlePreviewAdapter(it) }
            setRecycledViewPool(viewPool)
        }
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textView: TextView
        val recyclerView: RecyclerView

        init {
            itemView.setOnClickListener(this)
            textView = itemView.tv_item_title
            recyclerView = itemView.rv_sub_item
        }

        override fun onClick(view: View) {
            // TODO: open another activity with details
            Toast.makeText(
                view.context,
                "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT
            ).show()
        }
    }
}
