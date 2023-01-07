package application.data.main_activity

import application.domain.main_activiry.MainActivityRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor() : MainActivityRepository {

    override fun userLoggedIn(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}