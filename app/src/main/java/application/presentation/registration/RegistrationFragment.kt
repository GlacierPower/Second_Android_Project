package application.presentation.registration


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import application.model.User
import application.presentation.AuthenticationPageListener
import application.presentation.login.LoginFragment
import application.untils.AppConstants.hideSoftInput
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment(), RegisterView {


    private lateinit var pageListener: AuthenticationPageListener

    @Inject
    lateinit var registerPresenter: RegisterPresenter


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

        registerPresenter.setView(this)

        viewBinding.btnRegistration.setOnClickListener {
            val user = User(
                email = viewBinding.emailRegister.text.toString().trim(),
                username = viewBinding.userNameRegister.text.toString().trim(),
                password = viewBinding.passwordRegister.text.toString().trim(),
                confirmPassword = viewBinding.confirmPass.text.toString().trim()
            )
            registerPresenter.doRegister(user)

        }

    }


    override fun onUserNameEmpty() {
        viewBinding.layoutUserName.error = getString(R.string.username_empty)
        viewBinding.userNameRegister.requestFocus()
    }

    override fun onEmailEmpty() {
        viewBinding.layoutEmailRegister.error = getString(R.string.empty_email)
        viewBinding.emailRegister.requestFocus()
    }

    override fun onEmailInvalid() {
        viewBinding.layoutEmailRegister.error = getString(R.string.invalid_email)
        viewBinding.emailRegister.requestFocus()
    }

    override fun onPasswordEmpty() {
        viewBinding.layoutPasswordRegister.error = getString(R.string.pass_empty)
        viewBinding.passwordRegister.requestFocus()
    }

    override fun onPasswordToShort() {
        viewBinding.layoutPasswordRegister.error = getString(R.string.pass_short)
        viewBinding.passwordRegister.requestFocus()
    }

    override fun onConfirmPasswordEmpty() {
        viewBinding.layoutConfirm.error = getString(R.string.confirm_pass_empty)
        viewBinding.confirmPass.requestFocus()
    }

    override fun onConfirmPasswordNotMatch() {
        viewBinding.layoutConfirm.error = getString(R.string.confirm_password)
        viewBinding.confirmPass.requestFocus()
    }

    override fun onRegisterStart() {
        context?.hideSoftInput(viewBinding.emailRegister)
        viewBinding.btnRegistration.isEnabled = false
    }

    override fun onProgress(visibility: Int) {
        viewBinding.loading.visibility = visibility
        replaceFragment(parentFragmentManager,LoginFragment(),true)
    }


    override fun onRegisterSuccess() {
        viewBinding.rootView.showsnackBar(getString(R.string.user_reg))
    }

    override fun onRegisterFailed(error: String?) {
        viewBinding.rootView.showsnackBar(getString(R.string.ref_fail))
        viewBinding.btnRegistration.isEnabled
    }

}






