package com.dznow.home

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.article.ArticleActivity
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
        holder.article = article
    }

    inner class ViewHolder(itemView: View, var article : ArticleModel? = null) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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
            minutesRead = itemView.tv_article_minutes_read
            articleCover = itemView.tv_article_cover
        }

        override fun onClick(view: View) {
            val intent = Intent(view.context, ArticleActivity::class.java)
            intent.putExtra("sourceName", article?.source?.name)
            intent.putExtra("title", article?.title)
            intent.putExtra("content", article?.content)
            intent.putExtra("minutes_read", article?.minutes_read)
            intent.putExtra("cover_url", article?.cover_url)
            intent.putExtra("created_at", article?.created_at)
            intent.putExtra("url", article?.url)
            view.context.startActivity(intent)
        }
    }
}
