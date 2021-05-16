package com.example.geckoapplication.ui.main

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.geckoapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class SignupFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

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
            if (etName.text.toString().isEmpty() || etEmail.text.toString().isEmpty() ||
                    etPssword.text.toString().isEmpty() || etContact.text.toString().isEmpty()){
                Toast.makeText(context?.applicationContext, "There are fields empty", Toast.LENGTH_LONG).show()
            }else {
                if (!validateEmail(etEmail.text.toString())){
                    etEmail.setError("Incorrect Email")
                }else{
                    //Firebase Code
                    signUsers(etEmail, etPssword, etName, etContact)
                }
            }
        }
    }

    private fun validateEmail(email: String): Boolean{
        var pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun signUsers(email: EditText, psswd: EditText, name: EditText, contact: EditText){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email.text.toString(), psswd.text.toString())
            .addOnCompleteListener {
                if (it.isSuccessful){
                    db.collection("users").document(email.text.toString()).set(
                        hashMapOf(
                            "Name" to name.text.toString(),
                            "Email" to email.text.toString(),
                            "Contact" to contact.text.toString()
                        )
                    )
                    Toast.makeText(context?.applicationContext, "Your data has been saved succesfully", Toast.LENGTH_LONG).show()
                    cleanTextViews(email, psswd, name, contact)
                }else{
                    Toast.makeText(context?.applicationContext, "An error occurred authenticating the user", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun cleanTextViews(email: EditText, psswd: EditText, name: EditText, contact: EditText){
        email.setText("")
        psswd.setText("")
        name.setText("")
        contact.setText("")
    }

}