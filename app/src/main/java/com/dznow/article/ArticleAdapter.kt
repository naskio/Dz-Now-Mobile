package com.dznow.article

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dznow.R
import kotlinx.android.synthetic.main.layout_article.view.*

class ArticleAdapter (private val articles : List<ArticleModel>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context).inflate(R.layout.layout_article,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val child = articles[position]
        holder.source.text = child.source
        holder.date.text = child.date
        holder.title.text = child.title
        holder.time.text = child.time
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val source : TextView = itemView.tv_sub_item_source
        val date : TextView = itemView.tv_sub_item_date
        val title : TextView = itemView.tv_sub_item_title
        val time : TextView = itemView.tv_sub_item_time
    }
}