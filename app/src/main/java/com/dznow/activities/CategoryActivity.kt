package com.dznow.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton
import com.dznow.R
import com.dznow.models.ArticleModel
import com.dznow.models.CategoryModel
import com.dznow.recyclers.ArticlePreviewAdapter
import com.dznow.services.categoryService
import com.dznow.services.storage.ForYouCategories
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var articles: ArrayList<ArticleModel>
    private lateinit var articlesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val categoryId = intent.getIntExtra("categoryId", 0)
        val categoryName = intent.getStringExtra("categoryName")
        val categoryBackgroundUrl = intent.getStringExtra("categoryBackgroundUrl")
        val categoryBackgroundColor = intent.getStringExtra("categoryBackgroundColor")
        val categoryTextColor = intent.getStringExtra("categoryTextColor")
        articlesRecyclerView = this.findViewById(R.id.recyclerViewArticles) as RecyclerView
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryService(this::showCategoryActivity, this::showErrorDialog, categoryId, 0)
        toolbarTitle.text = categoryName
        buttonBack?.setOnClickListener {
            finish()
        }
        val category = CategoryModel(categoryId, categoryName, categoryBackgroundUrl, categoryBackgroundColor, categoryTextColor, null)
        if (ForYouCategories.getInstance().isStarred(category)) {
            buttonStarSetImage(buttonStar, true)
        }
        else {
            buttonStarSetImage(buttonStar, false)
        }
        buttonStar.setOnClickListener {
            if (ForYouCategories.getInstance().isStarred(category!!)) {
                ForYouCategories.getInstance().unStar(category!!)
                buttonStarSetImage(buttonStar, false)
            }
            else {
                category.articles = articles
                ForYouCategories.getInstance().star(category!!)
                buttonStarSetImage(buttonStar, true)
            }
        }
    }

    fun buttonStarSetImage (buttonStar : ImageButton, checked : Boolean) {
        if (!checked) {
            buttonStar.setImageResource(R.drawable.ic_outline_star_border_24px)
        }
        else {
            buttonStar.setImageResource(R.drawable.ic_baseline_star_24px)
        }
    }

    private fun showCategoryActivity(articles: ArrayList<ArticleModel>) {
        // displaying bookmarks
        this.articles = articles
        this.runOnUiThread {
            articlesRecyclerView.adapter = ArticlePreviewAdapter(articles)
        }
    }

    // TODO: use dialog
    private fun showErrorDialog() {
        print("Network Error")
    }
}