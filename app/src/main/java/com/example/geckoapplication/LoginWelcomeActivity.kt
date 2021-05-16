package com.example.geckoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterButton
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class LoginWelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_welcome)

        val email = FirebaseAuth.getInstance().currentUser?.email.toString()
        val btnLogout: ImageButton = findViewById(R.id.ivLogout)

        imageGlide(btnLogout)
        persistent(email)
    }

    private fun persistent(email: String){
        val tvEmail: TextView = findViewById(R.id.tvEmail)

        tvEmail.text = email

    }

    private fun imageGlide(btnLogout: ImageButton){

        val link = "https://img.icons8.com/material-outlined/24/000000/shutdown.png"
        Glide
            .with(this)
            .load(link)
            .circleCrop()
            .into(btnLogout)

        btnLogout.setOnClickListener {
            val main = Intent(this, MainActivity::class.java)
            FirebaseAuth.getInstance().signOut()
            startActivity(main)
            finish()
        }
    }

}