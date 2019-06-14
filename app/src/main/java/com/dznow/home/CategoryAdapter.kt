package com.dznow.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dznow.R
import com.dznow.models.CategoryModel
import kotlinx.android.synthetic.main.layout_home_category.view.*

class CategoryAdapter(private val categories : List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_home_category,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.textView.text = category.name
        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayout.VERTICAL, false)
            adapter = category.articles?.let { ArticleAdapter(it) }
            setRecycledViewPool(viewPool)
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.tv_item_title
        val recyclerView : RecyclerView = itemView.rv_sub_item
    }
}
