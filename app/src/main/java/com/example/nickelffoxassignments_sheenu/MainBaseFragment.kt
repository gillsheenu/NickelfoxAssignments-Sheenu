package com.example.nickelffoxassignments_sheenu

import android.app.appsearch.AppSearchResult
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nickelffoxassignments_sheenu.MainActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class MainBaseFragment : Fragment()/*Fragments_interface*/{
    var baseActivity: MainActivity? =null

companion object{
    lateinit var firebaseAuth: FirebaseAuth
}



    var providers= arrayListOf<AuthUI.IdpConfig>(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity=context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view =inflater.inflate(R.layout.fragment_main_base, container, false)

        firebaseAuth=FirebaseAuth.getInstance()
        return view
    }
    var signUpLauncher=registerForActivityResult(FirebaseAuthUIActivityResultContract()){
        this.onSignUpResult(it)
    }

    private fun onSignUpResult(result: FirebaseAuthUIAuthenticationResult?) {
        if(result!!.resultCode == AppSearchResult.RESULT_OK){
            findNavController().navigate(R.id.mainFragment)
        }else{
            Toast.makeText(context,"Authentication Failed", Toast.LENGTH_SHORT).show()
        }


    }

    suspend  public fun OptionsSignUp() {
        var signUpIntent=AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        withContext(Dispatchers.Main){
            signUpLauncher.launch(signUpIntent)
        }

    }


//    override fun onStart() {
//        super.onStart()
//        baseActivity?.showStatusBar(hidetoolbar())
//    }


}