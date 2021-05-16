package com.example.geckoapplication.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.geckoapplication.LoginWelcomeActivity
import com.example.geckoapplication.ProviderType
import com.example.geckoapplication.R
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SignupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_signup, container, false)

        emptyFieldsSignup(rootView)
        return rootView
    }

    private fun emptyFieldsSignup(v: View){
        val etName: EditText = v.findViewById(R.id.etNameSign)
        val etEmail: EditText = v.findViewById(R.id.etEmailSign)
        val etPssword: EditText = v.findViewById(R.id.etPasswordSign)
        val etContact: EditText = v.findViewById(R.id.etContactNumberSign)
        val btn_create_account: Button = v.findViewById(R.id.btnCreateAccountSign)

        btn_create_account.setOnClickListener {
            if (/*etName.text.toString().isEmpty() || */etEmail.text.toString().isEmpty() ||
                    etPssword.text.toString().isEmpty() /*|| etContact.text.toString().isEmpty()*/){
                Toast.makeText(context?.applicationContext, "There are fields empty", Toast.LENGTH_LONG).show()
            }else {
                if (!validateEmail(etEmail.text.toString())){
                    etEmail.setError("Incorrect Email")
                }else {
                    //Firebase Code
                    signUsers(etEmail.text.toString(), etPssword.text.toString())
                }
            }
        }
    }

    private fun validateEmail(email: String): Boolean{
        var pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun signUsers(email: String, psswd: String){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, psswd)
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
        val builder = AlertDialog.Builder(context?.applicationContext)
        builder.setTitle("Info")
        builder.setMessage("Your data has been saved succesfully")
        builder.setPositiveButton("To accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}