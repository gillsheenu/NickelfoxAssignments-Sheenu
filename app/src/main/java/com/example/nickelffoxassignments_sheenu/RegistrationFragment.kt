package com.example.nickelffoxassignments_sheenu


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.*
import kotlin.Exception


class RegistrationFragment : MainBaseFragment() {


    lateinit var signInLink: TextView
    lateinit var userName: EditText
    lateinit var emailAddress: EditText
    lateinit var mPassword: EditText
    lateinit var signUpBtn: Button
    lateinit var optionLink: TextView
    lateinit var mSignInViewModel:SignInViewModel
//    lateinit var navController:NavController
//    lateinit var navHostFragment:NavHostFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        mSignInViewModel=ViewModelProvider(this@RegistrationFragment).get(SignInViewModel::class.java)
        initalizeUI(view)
        redirect()


        signInLink.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment2)
        }
        signUpBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                signUpUser()
            }


        }
        optionLink.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                OptionsSignUp()

            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()


    }
//
//    override fun hidetoolbar(): Boolean {
//        return true


//    }

    private fun redirect() {
        mSignInViewModel.firebaseAuth.addAuthStateListener {
            if (it.currentUser != null) {
                findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                Toast.makeText(context, "user is already logged In", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend private fun signUpUser() {
        val name = userName.text.toString()
        val email = emailAddress.text.toString()
        val password = mPassword.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
            Toast.makeText(context, "Field can't be empty", Toast.LENGTH_SHORT).show()
        }


        withContext(Dispatchers.Main) {
            mSignInViewModel.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                    Toast.makeText(context, "user registered", Toast.LENGTH_SHORT).show()
                } else {
                    if (it.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(
                            context,
                            "user with this email already exists",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("AUTHEN", "duplicateEmail ")
                    } else {
                        Toast.makeText(
                            context,
                            "Authentication Failed: ${it.exception}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        Log.d("AUTHEN", "signUpUser:${it.exception} ")
                    }
                }
            }
        }
    }
    private fun initalizeUI(view: View) {
        signInLink = view.findViewById(R.id.tvSignInLink)
        userName = view.findViewById(R.id.etUserName)
        emailAddress = view.findViewById(R.id.etEmail)
        mPassword = view.findViewById(R.id.etPassword)
        signUpBtn = view.findViewById(R.id.btnSignUp)
        optionLink = view.findViewById(R.id.tvOptionClick)
    }
}