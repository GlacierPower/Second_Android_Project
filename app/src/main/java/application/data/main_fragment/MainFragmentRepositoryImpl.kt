package application.data.main_fragment

import application.domain.main_fragment.MainFragmentRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainFragmentRepositoryImpl @Inject constructor():MainFragmentRepository
     {
         override suspend fun logOut(auth: FirebaseAuth) {
             auth
                 .signOut()
         }
     }

