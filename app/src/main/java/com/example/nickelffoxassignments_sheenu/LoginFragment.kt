package com.example.nickelffoxassignments_sheenu

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class LoginFragment : MainBaseFragment() {

    lateinit var emailAddress: EditText
    lateinit var mPassword: EditText
    lateinit var logInBtn: Button
    lateinit var signUpLink: TextView
    lateinit var optionsLink:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view=inflater.inflate(R.layout.fragment_login, container, false)

        initalizeUI(view)
        signUpLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registrationFragment)
        }
        logInBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                signInUser()
            }

        }
        optionsLink.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch(){
                OptionsSignUp()
            }

        }

        return view
    }

//    override fun hidetoolbar(): Boolean {
//        return true
//    }

    suspend  private fun signInUser() {
        var email=emailAddress.text.toString()
        var password=mPassword.text.toString()
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            Toast.makeText(context,"Field can't be empty", Toast.LENGTH_SHORT).show()
        }
        withContext(Dispatchers.Main){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment2_to_mainFragment)
                    Toast.makeText(context, "user registered", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    Log.d("AUTHN", "signUpUser:${it.exception} ")
                }

            }

        }


    }

    private fun initalizeUI(view: View) {
        signUpLink= view.findViewById(R.id.tvSignUpLink)
        emailAddress=view.findViewById(R.id.etLoginEmail)
        mPassword=view.findViewById(R.id.etLoginPassword)
        logInBtn=view.findViewById(R.id.btnLogin)
        optionsLink=view.findViewById(R.id.tvOptionsLogin)
//        firebaseAuth=FirebaseAuth.getInstance()

    }



}