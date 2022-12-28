package application.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import application.model.User
import application.presentation.MainFragment
import application.presentation.forgot_pass.ForgotPassFragment
import application.presentation.registration.RegistrationFragment
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(), LoginView {

    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding!!

    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentLoginBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPresenter.setView(this)

        viewBinding.btnLogin.setOnClickListener {
            val user = User(
                email = viewBinding.email.text.toString().trim(),
                password = viewBinding.password.text.toString().trim()
            )

            loginPresenter.doLogin(user)

        }
        viewBinding.signUp.setOnClickListener {
            singUp()
        }

        viewBinding.forgotPass.setOnClickListener {
            forgotPass()
        }

    }


    private fun singUp() {
        replaceFragment(
            parentFragmentManager,
            RegistrationFragment(),
            true
        )

    }

    private fun forgotPass() {
        replaceFragment(
            parentFragmentManager,
            ForgotPassFragment(),
            true
        )
    }

    override fun onEmailEmpty() {
        viewBinding.layoutEmail.error = getString(R.string.empty_email)
        viewBinding.email.requestFocus()
    }

    override fun onEmailInvalid() {
        viewBinding.layoutEmail.error = getString(R.string.invalid_email)
        viewBinding.email.requestFocus()
    }

    override fun onPasswordEmpty() {
        viewBinding.layoutPassword.error = getString(R.string.pass_empty)
        viewBinding.password.requestFocus()
    }

    override fun onLoginSuccess(user: User) {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            replaceFragment(parentFragmentManager, MainFragment(), false)
        }
    }

    override fun onLoginFailed(error: String?) {
        viewBinding.logFragment.showsnackBar(getString(R.string.log_fail))
    }

    override fun userNotFound() {
        viewBinding.logFragment.showsnackBar(getString(R.string.user_not_found))
    }


}


