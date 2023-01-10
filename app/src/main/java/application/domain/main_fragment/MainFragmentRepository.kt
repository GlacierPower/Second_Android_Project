package application.domain.main_fragment

import com.google.firebase.auth.FirebaseAuth

interface MainFragmentRepository {
    suspend fun logOut(
        auth: FirebaseAuth
    )
}