package application.data.logout

import application.domain.logout.MainFragmentRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainFragmentRepositoryImpl @Inject constructor():MainFragmentRepository
     {
         override fun logOut(auth: FirebaseAuth) {
             auth
                 .signOut()
         }
     }

