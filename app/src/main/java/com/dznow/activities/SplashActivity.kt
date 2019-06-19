package com.dznow.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dznow.R
import com.dznow.models.ArticleModel
import com.dznow.models.CategoryModel
import com.dznow.services.homeService

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        homeService(this::showMainActivity, this::showErrorDialog)
    }

    private fun showMainActivity(latest: ArrayList<ArticleModel>, categories: ArrayList<CategoryModel>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("latest", latest)
        intent.putExtra("categories", categories)
        startActivity(intent)
        finish()
    }

    // TODO: use dialog
    private fun showErrorDialog() {
        this.runOnUiThread(Runnable {
            Toast.makeText(
                this
                , "Network Error", Toast.LENGTH_SHORT
            ).show()
        })
        Log.e("Network Error", "Network")
    }
}
