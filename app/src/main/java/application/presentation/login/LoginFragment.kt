package application.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import application.model.User
import application.presentation.main_fragment.MainFragment
import application.presentation.forgot_pass.ForgotPassFragment
import application.presentation.home.ItemsFragment
import application.presentation.registration.RegistrationFragment
import application.untils.AppConstants.isEmailValid
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentLoginBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnLogin.setOnClickListener {
            val user = User(
                email = viewBinding.email.text.toString().trim(),
                password = viewBinding.password.text.toString().trim()
            )
            if (validation(user)) {
                viewModel.loginUser(user)
            } else {
                view.showsnackBar(getString(R.string.log_fail))
            }
        }
        viewModel.nav.observe(viewLifecycleOwner) {
            replaceFragment(
                parentFragmentManager,
                ItemsFragment(),
                false
            )
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

    private fun validation(user: User): Boolean {
        var isValid = true
        if (user.email.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutEmail.error = getString(R.string.empty_email)
        }
        if (user.email?.isEmailValid() == false) {
            isValid = false
            viewBinding.layoutEmail.error = getString(R.string.invalid_email)
        }
        if (user.password.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutPassword.error = getString(R.string.pass_empty)
        }
        if ((user.password?.length ?: 0) < 6) {
            isValid = false
            viewBinding.layoutPassword.error = getString(R.string.pass_short)
        }
        return isValid
    }

}


