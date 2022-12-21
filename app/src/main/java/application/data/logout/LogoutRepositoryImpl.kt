package application.data.logout

import application.domain.logout.LogoutRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor():LogoutRepository
     {
         override fun logOut(auth: FirebaseAuth) {
             auth
                 .signOut()
         }
     }

