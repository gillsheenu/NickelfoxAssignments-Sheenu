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
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*


class RegistrationFragment : MainBaseFragment() {


    lateinit var signInLink: TextView
    lateinit var userName: EditText
    lateinit var emailAddress: EditText
    lateinit var mPassword: EditText
    lateinit var signUpBtn: Button
    lateinit var optionLink: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        initalizeUI(view)

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
        redirect()
    }
//
//    override fun hidetoolbar(): Boolean {
//        return true
//    }

    private fun redirect() {
        firebaseAuth.addAuthStateListener {
            if (firebaseAuth.currentUser != null) {
                findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                Toast.makeText(context, "user is already loggedIn", Toast.LENGTH_SHORT).show()
            }
        }
    }

   suspend  private fun signUpUser() {
        val name = userName.text.toString()
        val email = emailAddress.text.toString()
        val password = mPassword.text.toString()
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
            Toast.makeText(context, "Field can't be empty", Toast.LENGTH_SHORT).show()
        }


            withContext(Dispatchers.Main) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    if (it.isSuccessful) {

                        findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                        Toast.makeText(context, "user registered", Toast.LENGTH_SHORT).show()
                    } else {

                        Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("AUTHEN", "signUpUser:${it.exception} ")
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
        firebaseAuth = FirebaseAuth.getInstance()
        optionLink = view.findViewById(R.id.tvOptionClick)
    }
}