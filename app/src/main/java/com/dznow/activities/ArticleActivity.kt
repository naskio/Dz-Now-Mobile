package com.dznow.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dznow.R
import com.dznow.services.WEBSITE
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
        setContentView(R.layout.activity_article)
        toolbarTitle.text = intent.getStringExtra("sourceName")
        textViewArticleTitle.text = intent.getStringExtra("title")
        textViewArticleContent.text = intent.getStringExtra("content")
        textViewArticleMinutesRead.text =
            String.format(resources.getString(R.string.tv_sub_item_time), intent.getIntExtra("minutes_read", 0))
        val coverUrl = intent.getStringExtra("cover_url")
        Picasso.get().load(coverUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop()
            .into(imageViewArticleCover)
        textViewArticleTimeSince.text = timeSince(intent.getStringExtra("created_at"))

        buttonBack?.setOnClickListener {
            finish()
        }
        buttonShare?.setOnClickListener {
            shareAction(
                this,
                getString(R.string.share_article_title),
                intent.getStringExtra("title"),
                "${getString(R.string.share_article_content)} $WEBSITE${intent.getStringExtra("url")}"
            )
        }
    }
}
