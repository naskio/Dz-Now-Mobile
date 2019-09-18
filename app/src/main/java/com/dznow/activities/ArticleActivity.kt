package com.dznow.activities

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
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
import java.util.*
import android.content.Intent

// TODO: add get article API
// TODO: use get article from API instead (to solve TooLargeException)

class ArticleActivity : AppCompatActivity(),TextToSpeech.OnInitListener,
    TextToSpeech.OnUtteranceCompletedListener {

    private var tts: TextToSpeech? = null
    private var button_speech: ImageButton? = null
    private var text_view_article_title: TextView? = null
    private var text_view_article_content: TextView? = null
    private var isSpeaking: Boolean = false

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
        var lang = url.substring(1,3)
        button_speech = this.buttonSpeech
        if (lang != "fr") {
            button_speech!!.visibility = View.INVISIBLE
        }
        text_view_article_title = this.textViewArticleTitle
        text_view_article_content = this.textViewArticleContent
        //buttonSpeech!!.isEnabled = false
        tts = TextToSpeech(this, this)
        button_speech!!.setOnClickListener { speakOut() }
        toolbarTitle.text = sourceName
        textViewArticleTitle.text = title
        textViewArticleContent?.text = content
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
                "${getString(com.dznow.R.string.share_article_content)} $WEBSITE${url}"
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
        buttonShareContact?.setOnClickListener {
            val intent = Intent(this, ContactsActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
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

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.FRENCH)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            }
            else {
                buttonSpeech!!.isEnabled = true
            }
        }
        else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut() {
        if (!isSpeaking) {
            val text = textViewArticleTitle!!.text.toString() + textViewArticleContent!!.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
            button_speech!!.setImageResource(R.drawable.ic_outline_voice_over_off_24px)
            isSpeaking = true
        }
        else {
            tts!!.stop()
            button_speech!!.setImageResource(R.drawable.ic_outline_record_voice_over_24px)
            isSpeaking = false
        }
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onUtteranceCompleted(utteranceId: String) {
        isSpeaking = false
    }
}
