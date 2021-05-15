package com.example.geckoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class LoginWelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_welcome)

        val iv_logout: ImageView = findViewById(R.id.ivLogout)
        val link = "https://img.icons8.com/material-outlined/24/000000/shutdown.png"

        Glide
            .with(this)
            .load(link)
            .circleCrop()
            .into(iv_logout)
    }


}