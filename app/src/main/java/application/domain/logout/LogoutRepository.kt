package application.domain.logout

import com.google.firebase.auth.FirebaseAuth

interface LogoutRepository {
    fun logOut(
        auth: FirebaseAuth
    )
}