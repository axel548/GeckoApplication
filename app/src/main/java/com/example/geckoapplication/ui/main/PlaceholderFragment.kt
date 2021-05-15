package com.example.geckoapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.geckoapplication.LoginWelcomeActivity
import com.example.geckoapplication.R

class PlaceholderFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        val btn_login: Button = rootView.findViewById(R.id.btnLogin)
        btn_login.setOnClickListener {
            val intent = Intent(context?.applicationContext, LoginWelcomeActivity::class.java)
            activity?.startActivity(intent)
        }

        return rootView
    }
}