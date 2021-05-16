package com.example.geckoapplication.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.geckoapplication.LoginWelcomeActivity
import com.example.geckoapplication.ProviderType
import com.example.geckoapplication.R
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class PlaceholderFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        emptyFields(rootView)
        return rootView
    }

    private fun emptyFields(rootView: View){
        val etEmail: EditText = rootView.findViewById(R.id.etEmail)
        val etPsswd: EditText = rootView.findViewById(R.id.etPassword)
        val btn_login: Button = rootView.findViewById(R.id.btnLogin)

        btn_login.setOnClickListener {
            if (etEmail.text.toString().isEmpty() || !validateEmail(etEmail.text.toString())){
                etEmail.setError("Incorrect Email")
                Toast.makeText(context?.applicationContext, "Field Email is empty", Toast.LENGTH_LONG).show()
            }
            else if(etPsswd.text.toString().isEmpty()){
                Toast.makeText(context?.applicationContext, "Field Password is empty", Toast.LENGTH_LONG).show()
            }
            else {
                //Firebase Code
                loginUsers(etEmail.text.toString(), etPsswd.text.toString())
            }
        }
    }

    private fun validateEmail(email: String): Boolean{
        var pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun loginUsers(email: String, psswd: String){
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, psswd)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showHome()
                }else{
                    showAlert()
                }
            }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(context?.applicationContext)
        builder.setTitle("Error")
        builder.setMessage("An error occurred authenticating the user")
        builder.setPositiveButton("To accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(){
        val intent = Intent(context?.applicationContext, LoginWelcomeActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }
}