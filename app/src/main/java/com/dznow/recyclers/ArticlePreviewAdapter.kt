package com.dznow.recyclers

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.dznow.R
import com.dznow.activities.ArticleActivity
import com.dznow.models.ArticleModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_article_preview.view.*
import kotlin.collections.ArrayList
import com.dznow.services.WEBSITE
import com.dznow.services.helpers.Bookmarks
import com.dznow.utils.shareAction
import com.dznow.utils.timeSince
import kotlinx.android.synthetic.main.activity_article.*

class ArticlePreviewAdapter(private val articles: ArrayList<ArticleModel>) :
    RecyclerView.Adapter<ArticlePreviewAdapter.ArticlePreviewHolder>() {

    // creating the layout for the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlePreviewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_article_preview, parent, false)
        return ArticlePreviewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    // passing data to the view Holder
    override fun onBindViewHolder(holder: ArticlePreviewHolder, position: Int) {
        val article = articles[position]
        holder.sourceName.text = article.source?.name
        holder.createdAt.text = timeSince(article.created_at)
        holder.title.text = article.title
        holder.minutesRead.text = String.format(holder.minutes_read_template, article.minutes_read)
        Picasso.get().load(article.cover_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop()
            .into(holder.articleCover)
        holder.article = article
        if (Bookmarks.getInstance().isBookmarked(article)) {
            buttonBookmarkSetImage(holder.buttonBookmark, true)
        }
        else {
            buttonBookmarkSetImage(holder.buttonBookmark, false)
        }
    }

    private fun buttonBookmarkSetImage (buttonBookmark : ImageButton, checked : Boolean) {
        if (checked) {
            buttonBookmark.setImageResource(R.drawable.ic_outline_bookmark_24px)
        }
        else {
            buttonBookmark.setImageResource(R.drawable.ic_outline_bookmark_border_24px)
        }
    }

    // Article Preview Holder
    inner class ArticlePreviewHolder(itemView: View, var article: ArticleModel? = null) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val sourceName: TextView
        val createdAt: TextView
        val title: TextView
        val minutesRead: TextView
        val articleCover: ImageView
        val minutes_read_template: String
        val buttonBookmark : ImageButton

        init {
            itemView.setOnClickListener(this)
            itemView.buttonShare.setOnClickListener { buttonShareAction() }
            itemView.buttonBookmark.setOnClickListener { buttonBookmarkAction() }
            sourceName = itemView.textViewArticleSource
            createdAt = itemView.textViewArticleTimeSince
            title = itemView.textViewArticleTitle
            minutesRead = itemView.textViewArticleMinutesRead
            articleCover = itemView.imageViewArticleCover
            minutes_read_template = itemView.context.resources.getString(R.string.tv_sub_item_time)
            buttonBookmark = itemView.buttonBookmark
        }

        override fun onClick(view: View) {
            val intent = Intent(view.context, ArticleActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.putExtra("id", article?.id)
            intent.putExtra("sourceName", article?.source?.name)
            intent.putExtra("title", article?.title)
            intent.putExtra("content", article?.content)
            intent.putExtra("minutes_read", article?.minutes_read)
            intent.putExtra("cover_url", article?.cover_url)
            intent.putExtra("created_at", article?.created_at)
            intent.putExtra("url", article?.url)
            intent.putExtra("article", article)
            view.context.startActivity(intent)
        }

        fun buttonShareAction() {
            shareAction(
                itemView.context,
                itemView.context.getString(R.string.share_article_title),
                article?.title,
                "${itemView.context.getString(R.string.share_article_content)}${WEBSITE}${article?.url}"
            )
        }

        fun buttonBookmarkAction() {
            if (Bookmarks.getInstance().isBookmarked(article!!)) {
                Bookmarks.getInstance().unBookmark(article!!)
                buttonBookmarkSetImage(buttonBookmark, false)
            }
            else {
                Bookmarks.getInstance().bookmark(article!!)
                buttonBookmarkSetImage(buttonBookmark, true)
            }
        }
    }
}
