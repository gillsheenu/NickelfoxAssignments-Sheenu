package com.example.nickelffoxassignments_sheenu.Auth

import android.app.Activity
import android.app.appsearch.AppSearchResult
import android.app.appsearch.AppSearchResult.RESULT_OK
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nickelffoxassignments_sheenu.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import kotlinx.coroutines.*

class LoginFragment : Fragment() {

    lateinit var emailAddress: EditText
    lateinit var mPassword: EditText
    lateinit var logInBtn: Button
    lateinit var signUpLink: TextView
    lateinit var optionsLink:TextView
    lateinit var authViewModel: AuthViewModel

    var providers= arrayListOf<AuthUI.IdpConfig>(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view=inflater.inflate(R.layout.fragment_login, container, false)
        authViewModel=ViewModelProvider(this@LoginFragment, AuthViewModelFactory(requireActivity().application)).get(AuthViewModel::class.java)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.userMutableLiveData.observe(viewLifecycleOwner, Observer {
            if(it !=null){
                findNavController().navigate(R.id.action_loginFragment2_to_mainFragment)
            }else{
                Toast.makeText(activity,"user is logout",Toast.LENGTH_SHORT).show()
            }
        })
    }


    suspend  private fun signInUser() {
        var email=emailAddress.text.toString()
        var password=mPassword.text.toString()
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            Toast.makeText(context,"Field can't be empty", Toast.LENGTH_SHORT).show()
        }
        authViewModel.login(email, password)

    }

       fun OptionsSignUp() {
        var signUpIntent= AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
            signUpLauncher.launch(signUpIntent)


    }
    var signUpLauncher=registerForActivityResult(FirebaseAuthUIActivityResultContract()){
        this.onSignUpResult(it)
    }
    fun onSignUpResult(result: FirebaseAuthUIAuthenticationResult) {
        if(result.resultCode== Activity.RESULT_OK){
            authViewModel.addCurrentUser()

        }else{
            Toast.makeText(context,"Authentication Failed", Toast.LENGTH_SHORT).show()
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