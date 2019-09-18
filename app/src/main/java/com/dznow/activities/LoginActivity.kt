package com.dznow.activities

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionProvider
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.dznow.R
import com.dznow.models.ArticleModel
import com.dznow.models.CategoryModel
import com.dznow.services.homeService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.SignInButton


class LoginActivity : AppCompatActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var token: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener { v ->
            updateUi()
            signInWithGmail()
        }

        val skipButton = findViewById<TextView>(R.id.button_skip)
        skipButton.setOnClickListener { v ->
            updateUi()
            homeService(this::showMainActivity, this::showErrorDialog)
        }
    }

    private fun showMainActivity(
        latest: ArrayList<ArticleModel>,
        categories: ArrayList<CategoryModel>
    ) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("token", token)
        intent.putExtra("latest", latest)
        intent.putExtra("categories", categories)
        startActivity(intent)
        finish()
    }

    private fun showErrorDialog() {
        this.runOnUiThread(Runnable {
            Toast.makeText(
                this
                , "Network Error", Toast.LENGTH_SHORT
            ).show()
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("latest", null)
//            intent.putExtra("categories", null)
//            startActivity(intent)
//            finish()
        })
        Log.e("Network Error", "Network")
    }

    private fun signInWithGmail() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        homeService(this::showMainActivity, this::showErrorDialog)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            token = account?.idToken
            Log.d("HELLO TOKEN", account?.idToken)
            Log.d("EMAIL", account?.email)
            Log.d("ID", account?.id)
            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERROR CODE", "signInResult:failed code=" + e.statusCode)
        } finally {
            homeService(this::showMainActivity, this::showErrorDialog)
        }

    }

    private fun updateUi() {
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        val skipButton = findViewById<TextView>(R.id.button_skip)
        val progressBar = findViewById<ProgressBar>(R.id.pBar)
        signInButton.visibility = View.GONE
        skipButton.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }


    companion object {
        const val RC_SIGN_IN = 12345
    }
}
