package application.presentation.forgot_pass

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import application.data.AuthRepositoryCallBack
import application.domain.forgot_pass.ForgotPassInteractor
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    private val forgotPassInteractor: ForgotPassInteractor
) : ViewModel() {

    private val _nav = MutableLiveData<Unit>()
    val nav: LiveData<Unit> = _nav

    fun resetPass(user: User) {
        forgotPassInteractor.forgotPassword(user, object : AuthRepositoryCallBack {
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