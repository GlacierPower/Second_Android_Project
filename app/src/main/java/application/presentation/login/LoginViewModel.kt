package application.presentation.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import application.data.AuthRepositoryCallBack
import application.domain.login.LoginInteractor
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor
) : ViewModel() {

    private val _nav = MutableLiveData<Unit>()
    val nav: LiveData<Unit> = _nav

    fun loginUser(user: User) {
        loginInteractor.loginUser(user, object : AuthRepositoryCallBack {
            override fun success(user: User) {
                Log.i(TAG, "Success")
            }

            override fun fail(error: String?) {
                Log.i(TAG, "Fail")
            }

        }, FirebaseAuth.getInstance())
        _nav.value = Unit
    }

}