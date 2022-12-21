package application.presentation.registration

import android.util.Patterns
import android.view.View
import application.data.AuthRepositoryCallBack
import application.domain.registration.RegistrationInteractor
import application.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class RegisterPresenter @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
) {
    fun View.showsnackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }

    private lateinit var view: RegisterView
    fun doRegister(user: User) {

        if (user.email!!.isBlank() || user.password!!.isBlank() || user.confirmPassword!!.isBlank()) {
            return
        }
        if (user.password != user.confirmPassword) {
            return
        } else {
            registrationInteractor.registerUser(user, object :
                AuthRepositoryCallBack {
                override fun success(user: User) {
                }

                override fun fail(error: String?) {
                    view.onRegisterFailed("Lol")
                }
            }, FirebaseAuth.getInstance())
            registrationInteractor.verifyEmail(FirebaseAuth.getInstance())
//            NavigationOnFragment.replaceFragment(parentFragmentManager,
//                LoginFragment(),
//                true)

        }

    }


    private fun String.isEmailValid() =
        this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
