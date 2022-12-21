package application.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import application.data.login.LoginRepositoryImpl
import application.domain.login.LoginInteractor
import application.model.User
import application.presentation.registration.RegistrationFragment
import application.untils.AppConstants.showsnackBar
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(), LoginView {

    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding

    private lateinit var pageListener: application.presentation.AuthenticationPageListener

    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentLoginBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        loginPresenter = LoginPresenter(
            LoginInteractor(LoginRepositoryImpl())
        )
        viewBinding?.btnLogin?.setOnClickListener {
            val user = User(
                email = viewBinding?.email?.text.toString().trim(),
                password = viewBinding?.password?.text.toString().trim()
            )
            loginPresenter.doLogin(user)
//            replaceFragment(parentFragmentManager, MainFragment(), false)

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
            .add(R.id.activityContainer, application.presentation.forgot_pass.ForgotPassFragment())
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
