package application.presentation.registration


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import application.model.User
import application.presentation.login.LoginFragment
import application.untils.AppConstants.isEmailValid
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {


    private val viewModel: RegistrationViewModel by viewModels()

    private var _viewBinding: FragmentRegistrationBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentRegistrationBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnRegistration.setOnClickListener {
            val user = User(
                email = viewBinding.emailRegister.text.toString().trim(),
                username = viewBinding.userNameRegister.text.toString().trim(),
                password = viewBinding.passwordRegister.text.toString().trim(),
                confirmPassword = viewBinding.confirmPass.text.toString().trim()
            )
            if (validation(user)) {
                viewModel.registrationUser(user)
                view.showsnackBar(getString(R.string.user_reg))
            }
        }
        viewModel.nav.observe(viewLifecycleOwner) {
            replaceFragment(
                parentFragmentManager,
                LoginFragment(),
                false
            )
        }

    }

    fun validation(user: User): Boolean {
        var isValid = true
        if (user.username.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutUserName.error = getString(R.string.username_empty)
        }
        if (user.email.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutEmailRegister.error = getString(R.string.empty_email)
        }
        if (user.email?.isEmailValid() == false) {
            isValid = false
            viewBinding.layoutEmailRegister.error = getString(R.string.invalid_email)
        }
        if (user.password.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutPasswordRegister.error = getString(R.string.pass_empty)
        }
        if ((user.password?.length ?: 0) < 6) {
            isValid = false
            viewBinding.layoutPasswordRegister.error = getString(R.string.pass_short)
        }

        if (user.confirmPassword.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutConfirm.error = getString(R.string.confirm_pass_empty)
        }

        if (user.confirmPassword != user.password) {
            isValid = false
            viewBinding.layoutConfirm.error = getString(R.string.confirm_password)
        }
        return isValid
    }
}








