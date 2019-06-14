package com.dznow.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dznow.R
import com.dznow.models.ArticleModel
import kotlinx.android.synthetic.main.layout_home_article.view.*

class ArticleAdapter (private val articles : List<ArticleModel>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context).inflate(R.layout.layout_home_article,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.source.text = article.source.name
        holder.date.text = article.created_at
        holder.title.text = article.title
        holder.time.text = article.minutes_read.toString()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val source : TextView = itemView.tv_sub_item_source
        val date : TextView = itemView.tv_sub_item_date
        val title : TextView = itemView.tv_sub_item_title
        val time : TextView = itemView.tv_sub_item_time
    }
}
