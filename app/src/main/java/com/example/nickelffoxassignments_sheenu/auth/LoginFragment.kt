package com.example.nickelffoxassignments_sheenu.auth

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nickelffoxassignments_sheenu.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import kotlinx.coroutines.*

class LoginFragment : Fragment() {

    private lateinit var emailAddress: EditText
    private lateinit var mPassword: EditText
    private lateinit var logInBtn: Button
    private lateinit var signUpLink: TextView
    private lateinit var optionsLink:TextView
    private lateinit var authViewModel: AuthViewModel

    private var providers= arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_login, container, false)
        authViewModel= ViewModelProvider(this@LoginFragment, AuthViewModelFactory(requireActivity().application))[AuthViewModel::class.java]

        initializeUI(view)
        signUpLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registrationFragment)
        }
        logInBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                signInUser()
            }

        }
        optionsLink.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                optionsSignUp()
            }

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.userMutableLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_loginFragment2_to_mainFragment)
            } else {
                Toast.makeText(activity, "user is logout", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun signInUser() {
        val email=emailAddress.text.toString()
        val password=mPassword.text.toString()
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            Toast.makeText(context,"Field can't be empty", Toast.LENGTH_SHORT).show()
        }
        authViewModel.login(email, password)

    }

       private fun optionsSignUp() {
        val signUpIntent= AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
            signUpLauncher.launch(signUpIntent)


    }
    private var signUpLauncher=registerForActivityResult(FirebaseAuthUIActivityResultContract()){
        this.onSignUpResult(it)
    }
    private fun onSignUpResult(result: FirebaseAuthUIAuthenticationResult) {
        if(result.resultCode== Activity.RESULT_OK){
            authViewModel.addCurrentUser()

        }else{
            Toast.makeText(context,"Authentication Failed", Toast.LENGTH_SHORT).show()
        }


    }



    private fun initializeUI(view: View) {
        signUpLink= view.findViewById(R.id.tvSignUpLink)
        emailAddress=view.findViewById(R.id.etLoginEmail)
        mPassword=view.findViewById(R.id.etLoginPassword)
        logInBtn=view.findViewById(R.id.btnLogin)
        optionsLink=view.findViewById(R.id.tvOptionsLogin)
//        firebaseAuth=FirebaseAuth.getInstance()

    }



}