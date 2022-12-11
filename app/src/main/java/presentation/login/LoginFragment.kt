package presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentLoginBinding
import data.repository.entity.User
import data.repository.repository.LoginRepository
import presentation.AuthenticationPageListener
import presentation.forgot_pass.ForgotPassFragment
import presentation.logout.MainFragment
import presentation.registration.RegistrationFragment
import untils.AppConstants.showsnackBar


class LoginFragment : Fragment(), LoginView {


    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding

    private lateinit var pageListener: AuthenticationPageListener
    private lateinit var loginPresenter: LoginPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentLoginBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        loginPresenter = LoginPresenter(LoginRepository(FirebaseAuth.getInstance()))
        viewBinding?.btnLogin?.setOnClickListener {
            val user = User(
                email = viewBinding?.email?.text.toString().trim(),
                password = viewBinding?.password?.text.toString().trim()
            )
            loginPresenter.doLogin(user)
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, MainFragment())
                .commit()

        }
        viewBinding?.signUp?.setOnClickListener {
            singUp()
        }

        viewBinding?.forgotPass?.setOnClickListener {
            forgotPass()
        }

    }


    private fun singUp() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.activityContainer, RegistrationFragment())
            .addToBackStack("add")
            .commit()
    }

    private fun forgotPass() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.activityContainer, ForgotPassFragment())
            .addToBackStack("add")
            .commit()
    }

    override fun onEmailEmpty() {
        viewBinding?.layoutEmail?.error = getString(R.string.empty_email)
        viewBinding?.email?.requestFocus()
    }

    override fun onEmailInvalid() {
        viewBinding?.layoutEmail?.error = getString(R.string.invalid_email)
        viewBinding?.email?.requestFocus()
    }

    override fun onPasswordEmpty() {
        viewBinding?.layoutPassword?.error = getString(R.string.pass_empty)
        viewBinding?.password?.requestFocus()
    }

    override fun onLoginSuccess(user: User) {
        pageListener.authenticateSuccess(user)
    }

    override fun onLoginFailed(error: String?) {
        view?.showsnackBar(getString(R.string.log_fail))
    }

}
