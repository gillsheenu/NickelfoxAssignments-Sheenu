package com.example.nickelffoxassignments_sheenu.Auth


import android.app.Activity
import android.app.appsearch.AppSearchResult
import android.app.appsearch.AppSearchResult.RESULT_OK
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nickelffoxassignments_sheenu.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import kotlinx.coroutines.*


class RegistrationFragment : Fragment() {

   lateinit var signInLink: TextView
    lateinit var userName: EditText
    lateinit var emailAddress: EditText
    lateinit var mPassword: EditText
    lateinit var signUpBtn: Button
    lateinit var optionLink: TextView
    lateinit var authViewModel:AuthViewModel

    var providers= arrayListOf<AuthUI.IdpConfig>(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        authViewModel=ViewModelProvider(this@RegistrationFragment,AuthViewModelFactory(requireActivity().application)).get(AuthViewModel::class.java)
        initalizeUI(view)

        signInLink.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment2)
        }

        signUpBtn.setOnClickListener {

                signUpUser()
        }

        optionLink.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                OptionsSignUp()

            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.userMutableLiveData.observe(viewLifecycleOwner, Observer {
            if(it !=null){
                findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
            }else{
                Toast.makeText(activity,"user is logout",Toast.LENGTH_SHORT).show()
            }
        })
    }


     private fun signUpUser() {
        val name = userName.text.toString()
        val email = emailAddress.text.toString()
        val password = mPassword.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
            Toast.makeText(context, "Field can't be empty", Toast.LENGTH_SHORT).show()
        }
         authViewModel.register(email, password)
    }


     suspend fun OptionsSignUp() {
        var signUpIntent= AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
         withContext(Dispatchers.IO) {
             signUpLauncher.launch(signUpIntent)
         }
    }

    var signUpLauncher=registerForActivityResult(FirebaseAuthUIActivityResultContract()){
        onSignUpResult(it)
        Log.d("RESU", ":${it.resultCode} ")
    }

    fun onSignUpResult(result: FirebaseAuthUIAuthenticationResult) {
        if(result.resultCode==Activity.RESULT_OK){
            authViewModel.addCurrentUser()
        }else{
            Log.d("RESU", "onSignUpResult: ${result.resultCode} ")
            Toast.makeText(context,"Authentication Failed", Toast.LENGTH_SHORT).show()
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