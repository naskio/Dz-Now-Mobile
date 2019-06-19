package com.dznow.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.dznow.R
import com.dznow.models.ArticleModel
import com.dznow.models.SourceModel
import com.dznow.services.WEBSITE
import com.dznow.services.storage.Bookmarks
import com.dznow.utils.shareAction
import com.dznow.utils.timeSince
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.activity_article.imageViewArticleCover
import kotlinx.android.synthetic.main.activity_article.textViewArticleTimeSince
import kotlinx.android.synthetic.main.activity_article.textViewArticleMinutesRead
import kotlinx.android.synthetic.main.activity_article.textViewArticleTitle

// TODO: add get article API
// TODO: use get article from API instead (to solve TooLargeException)

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val minutesRead = intent.getIntExtra("minutes_read", 0)
        val coverUrl = intent.getStringExtra("cover_url")
        val createdAt = intent.getStringExtra("created_at")
        val sourceName = intent.getStringExtra("sourceName")
        val url = intent.getStringExtra("url")
        val source = SourceModel(0, sourceName, "", "", "", "", null)
        val article = ArticleModel(id, title, content, minutesRead, coverUrl, createdAt, null, source, url)
        setContentView(R.layout.activity_article)
        toolbarTitle.text = sourceName
        textViewArticleTitle.text = title
        textViewArticleContent.text = content
        textViewArticleMinutesRead.text = String.format(resources.getString(R.string.tv_sub_item_time), minutesRead)
        Picasso.get().load(coverUrl)
            .placeholder(R.drawable.placeholder_gray)
            .fit()
            .centerCrop()
            .into(imageViewArticleCover)
        textViewArticleTimeSince.text = timeSince(createdAt)
        if (Bookmarks.getInstance().isBookmarked(article)) {
            buttonBookmarkSetImage(buttonBookmark, true)
        }
        else {
            buttonBookmarkSetImage(buttonBookmark, false)
        }
        buttonBack?.setOnClickListener {
            finish()
        }
        buttonShare?.setOnClickListener {
            shareAction(
                this,
                getString(R.string.share_article_title),
                intent.getStringExtra("title"),
                "${getString(R.string.share_article_content)} $WEBSITE${url}"
            )
        }
        buttonBookmark?.setOnClickListener {
            if (Bookmarks.getInstance().isBookmarked(article)) {
                Bookmarks.getInstance().unBookmark(article)
                buttonBookmarkSetImage(buttonBookmark, false)
            }
            else {
                Bookmarks.getInstance().bookmark(article)
                buttonBookmarkSetImage(buttonBookmark, true)
            }
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
}
