package com.dznow.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.article.ArticleModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
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
        holder.sourceName.text = article.source.name
        holder.createdAt.text = article.created_at
        holder.title.text = article.title
        holder.minutesRead.text = article.minutes_read.toString()
        Picasso.get().load(article.cover_url).into(holder.articleCover)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val sourceName : TextView
        val createdAt : TextView
        val title : TextView
        val minutesRead : TextView
        val articleCover : ImageView

        init {
            itemView.setOnClickListener(this)
            sourceName = itemView.tv_article_source_name
            createdAt =  itemView.tv_article_created_at
            title = itemView.tv_article_title
            minutesRead = itemView.tv_article_created_at
            articleCover = itemView.tv_article_cover
        }

        override fun onClick(view: View) {
            Toast.makeText(view.context,
                "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
