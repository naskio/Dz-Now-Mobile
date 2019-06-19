package com.dznow.recyclers

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dznow.R
import com.dznow.activities.CategoryActivity
import com.dznow.models.CategoryModel
import com.dznow.services.categoriesAPI
import com.dznow.services.categoryAPI
import com.dznow.services.storage.ForYouCategories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_category_preview.view.*

class CategoryPreviewAdapter(private val categories: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryPreviewAdapter.CategoryPreviewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPreviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_preview, parent, false)
        return CategoryPreviewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryPreviewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name
        Picasso.get().load(category.background_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop()
            .into(holder.icon)
        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayout.VERTICAL, false)
            adapter = category.articles?.let { ArticlePreviewAdapter(it) }
            setRecycledViewPool(viewPool)
        }
        holder.category = category
        if (ForYouCategories.getInstance().isStarred(category)) {
            buttonStarSetImage(holder.buttonStar, true)
        }
        else {
            buttonStarSetImage(holder.buttonStar, false)
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

    inner class CategoryPreviewHolder(itemView: View, var category: CategoryModel? = null) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val categoryName: TextView
        val recyclerView: RecyclerView
        val buttonShowMore: Button
        val icon: ImageView
        val buttonStar : ImageButton

        init {
            itemView.setOnClickListener(this)
            itemView.buttonStar.setOnClickListener { buttonStarAction() }
            categoryName = itemView.tv_item_title
            recyclerView = itemView.recyclerViewArticles
            buttonShowMore = itemView.buttonShowMore
            icon = itemView.categoryBackground
            buttonShowMore.setOnClickListener {
                onClick(itemView)
            }
            buttonStar = itemView.buttonStar
        }

        override fun onClick(view: View) {
            val intent = Intent(view.context, CategoryActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.putExtra("categoryId", category?.id)
            intent.putExtra("categoryName", category?.name)
            intent.putExtra("categoryBackgroundUrl", category?.background_url)
            intent.putExtra("categoryBackgroundColor", category?.background_color)
            intent.putExtra("categoryTextColor", category?.text_color)
            intent.putExtra("categoryArticles", category?.articles)
            view.context.startActivity(intent)
        }

        fun buttonStarAction () {
            if (ForYouCategories.getInstance().isStarred(category!!)) {
                ForYouCategories.getInstance().unStar(category!!)
                buttonStarSetImage(buttonStar, false)
            }
            else {
                ForYouCategories.getInstance().star(category!!)
                buttonStarSetImage(buttonStar, true)
            }
        }
    }
}
