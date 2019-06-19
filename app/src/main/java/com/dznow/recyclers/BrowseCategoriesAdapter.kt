package com.dznow.recyclers

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dznow.R
import com.dznow.activities.CategoryActivity
import com.dznow.models.CategoryModel
import com.dznow.services.storage.ForYouCategories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_browse_category.view.*

class BrowseCategoriesAdapter(private val categories: List<CategoryModel>) :
    RecyclerView.Adapter<BrowseCategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.layout_browse_category, null)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.targetName.text = category.name
        Picasso.get().load(category.background_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .centerCrop()
            .into(holder.categoryCover)
        holder.category = category
    }

    fun buttonStarSetImage (buttonStar : ImageButton, checked : Boolean) {
        if (!checked) {
            buttonStar.setImageResource(R.drawable.ic_outline_star_border_24px)
        }
        else {
            buttonStar.setImageResource(R.drawable.ic_baseline_star_24px)
        }
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(itemView: View, var category : CategoryModel? = null) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var targetName: TextView
        var categoryCover: ImageView
        val buttonStar : ImageButton
        val cardViewCategory : RelativeLayout

        init {
            itemView.setOnClickListener(this)
            itemView.buttonStar.setOnClickListener { buttonStarAction () }
            targetName = itemView.target_name
            categoryCover = itemView.tv_item_cover
            buttonStar = itemView.buttonStar
            cardViewCategory = itemView.card_view_category
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
