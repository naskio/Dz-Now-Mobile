package com.dznow.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dznow.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.activity_article.tv_article_cover
import kotlinx.android.synthetic.main.activity_article.tv_article_created_at
import kotlinx.android.synthetic.main.activity_article.tv_article_minutes_read
import kotlinx.android.synthetic.main.activity_article.tv_article_title
import kotlinx.android.synthetic.main.layout_article_preview.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        // tv_article_source_name.text = intent.getStringExtra("sourceName")
        tv_article_title.text = intent.getStringExtra("title")
        tv_article_content.text = intent.getStringExtra("content")
        tv_article_minutes_read.text = intent.getStringExtra("minutes_read")
        val coverUrl = intent.getStringExtra("cover_url")
        Picasso.get().load(coverUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop()
            .into(tv_article_cover)
        tv_article_created_at.text = intent.getStringExtra("created_at")
        // val url = intent.getStringExtra("url")
    }
}
