package data.repository.repository

import com.google.firebase.auth.FirebaseAuth

class LogoutRepository(private val auth: FirebaseAuth) {
    fun logOut() {
        auth
            .signOut()
    }
}

