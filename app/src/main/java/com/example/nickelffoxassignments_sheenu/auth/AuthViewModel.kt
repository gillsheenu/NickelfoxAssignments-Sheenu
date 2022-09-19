package com.example.nickelffoxassignments_sheenu.auth

import android.app.Application
import androidx.lifecycle.ViewModel

class AuthViewModel(val application: Application):ViewModel() {
    private var authRepository=AuthenticationRepository(application)

    var userMutableLiveData = authRepository.firebaseMutableLiveData
    var statusMutableLiveData=authRepository.loggedMutableLiveData

    fun register(email:String,password:String){
        authRepository.registerUser(email, password)
    }
    fun login(email: String,password: String){
        authRepository.loginUser(email,password)
    }
    fun logout(){
        authRepository.logout()
    }

  fun addCurrentUser(){
      authRepository.addUser()
  }

}