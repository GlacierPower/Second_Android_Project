package application.domain.logout

import com.google.firebase.auth.FirebaseAuth

interface MainFragmentRepository {
    fun logOut(
        auth: FirebaseAuth
    )
}