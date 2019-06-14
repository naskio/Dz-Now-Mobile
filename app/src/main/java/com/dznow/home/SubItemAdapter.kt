package com.dznow.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.article.ArticleModel
import kotlinx.android.synthetic.main.layout_home_sub_item.view.*

class SubItemAdapter (private val articles : List<ArticleModel>) : RecyclerView.Adapter<SubItemAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context).inflate(R.layout.layout_home_sub_item,parent,false)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val source : TextView
        val date : TextView
        val title : TextView
        val time : TextView

        init {
            itemView.setOnClickListener(this)
            source = itemView.tv_sub_item_source
            date =  itemView.tv_sub_item_date
            title = itemView.tv_sub_item_title
            time = itemView.tv_sub_item_time
        }

        override fun onClick(view: View) {
            Toast.makeText(view.context,
                "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
