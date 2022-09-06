package com.example.nickelffoxassignments_sheenu

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {

    var firebaseAuth=FirebaseAuth.getInstance()



    fun logout(){
        firebaseAuth.signOut()
    }


}