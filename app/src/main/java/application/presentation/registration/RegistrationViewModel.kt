package application.presentation.registration

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import application.data.AuthRepositoryCallBack
import application.domain.registration.RegistrationInteractor
import application.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
) : ViewModel() {

    private val _nav = MutableLiveData<Unit>()
    val nav: LiveData<Unit> = _nav

    fun registrationUser(user: User) {
        registrationInteractor.registerUser(user, object : AuthRepositoryCallBack {
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