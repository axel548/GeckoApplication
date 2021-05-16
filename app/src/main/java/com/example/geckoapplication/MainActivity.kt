package com.example.geckoapplication

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.geckoapplication.ui.main.SectionsPagerAdapter
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validateUser()

        viewtabs()

    }

    private fun viewtabs() {
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    private fun validateUser(){
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        val login = Intent(this, LoginWelcomeActivity::class.java)
        if (user != null){
            startActivity(login)
            finish()
        }
    }


}