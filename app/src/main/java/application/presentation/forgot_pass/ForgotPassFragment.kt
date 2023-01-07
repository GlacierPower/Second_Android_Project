package application.presentation.forgot_pass

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
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentForgotPassBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPassFragment : Fragment() {

    private val viewModel: ForgotPassViewModel by viewModels()

    private var _viewBinding: FragmentForgotPassBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentForgotPassBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnForgot.setOnClickListener {
            val user = User(
                email = viewBinding.emailForfot.text.toString().trim()
            )
            if (validation(user)) {
                viewModel.resetPass(user)
                view.showsnackBar(getString(R.string.reset_suc))
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
        if (user.email.isNullOrEmpty()) {
            isValid = false
            viewBinding.layoutEmailForgot.error = getString(R.string.empty_email)
        }
        if (user.email?.isEmailValid() == false) {
            isValid = false
            viewBinding.layoutEmailForgot.error = getString(R.string.invalid_email)
        }
        return isValid
    }


}
