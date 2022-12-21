package application.domain.login

import androidx.fragment.app.FragmentManager
import application.data.AuthRepositoryCallBack
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Provides

interface LoginRepository {

    fun loginUser(
        user: User,
        listener : AuthRepositoryCallBack,
        auth: FirebaseAuth,
    )



}