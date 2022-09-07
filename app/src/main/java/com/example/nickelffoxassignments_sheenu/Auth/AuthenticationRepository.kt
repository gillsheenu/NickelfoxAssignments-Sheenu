package com.example.nickelffoxassignments_sheenu.Auth

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Application
import android.app.appsearch.AppSearchResult
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport

class AuthenticationRepository(var application: Application) {

    var firebaseMutableLiveData = MutableLiveData<FirebaseUser>()

    var loggedMutableLiveData = MutableLiveData<Boolean>()

    var firebaseAuth = FirebaseAuth.getInstance()


    init {
        if (firebaseAuth.currentUser != null) {
            firebaseMutableLiveData.postValue(firebaseAuth.currentUser)
        }
    }

    fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseMutableLiveData.postValue(firebaseAuth.currentUser)
            } else {
                if (it.exception is FirebaseAuthUserCollisionException) {
                    Toast.makeText(application, "user already exist", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(application, "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseMutableLiveData.postValue(firebaseAuth.currentUser)
            } else {
                Toast.makeText(
                    application,
                    "LoginFailed:${it.exception!!.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    fun logout() {
        firebaseAuth.signOut()
        AuthUI.getInstance().signOut(application)
        loggedMutableLiveData.postValue(true)
//        firebaseMutableLiveData.postValue(firebaseAuth.currentUser)
    }

    fun addUser() {
        if (firebaseAuth.currentUser != null) {
            firebaseMutableLiveData.postValue(firebaseAuth.currentUser)
        }
    }
}









