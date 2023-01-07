package application.domain.main_activiry

import com.google.firebase.auth.FirebaseAuth

interface MainActivityRepository {
    fun userLoggedIn():FirebaseAuth
}