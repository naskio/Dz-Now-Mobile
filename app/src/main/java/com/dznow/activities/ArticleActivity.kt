package com.dznow.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dznow.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.activity_article.imageViewArticleCover
import kotlinx.android.synthetic.main.activity_article.textViewArticleTimeSince
import kotlinx.android.synthetic.main.activity_article.textViewArticleMinutesRead
import kotlinx.android.synthetic.main.activity_article.textViewArticleTitle

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        // tv_article_source_name.text = intent.getStringExtra("sourceName")
        textViewArticleTitle.text = intent.getStringExtra("title")
        tv_article_content.text = intent.getStringExtra("content")
        textViewArticleMinutesRead.text = intent.getStringExtra("minutes_read")
        val coverUrl = intent.getStringExtra("cover_url")
        Picasso.get().load(coverUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop()
            .into(imageViewArticleCover)
        textViewArticleTimeSince.text = intent.getStringExtra("created_at")
        // val url = intent.getStringExtra("url")
    }
}
