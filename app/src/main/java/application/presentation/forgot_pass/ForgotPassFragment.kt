package application.presentation.forgot_pass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import application.model.User
import application.untils.AppConstants.showsnackBar
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentForgotPassBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPassFragment : Fragment(), ForgotPassView {

    private var _viewBinding: FragmentForgotPassBinding? = null
    private val viewBinding get() = _viewBinding!!

    @Inject
    lateinit var forgotPassPresenter: ForgotPassPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentForgotPassBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        forgotPassPresenter.setView(this)


        viewBinding.btnForgot.setOnClickListener {
            val user = User(
                email = viewBinding.emailForfot.text.toString().trim()
            )
           forgotPassPresenter.doReset(user)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onEmailEmpty() {
        viewBinding.layoutEmailForgot.error = getString(R.string.empty_email)
        viewBinding.forgotPass.requestFocus()
    }

    override fun onEmailInvalid() {
        viewBinding.layoutEmailForgot.error = getString(R.string.invalid_email)
        viewBinding.forgotPass.requestFocus()
    }

    override fun onResetSuccess(user: User) {
        viewBinding.forgotPass.showsnackBar(getString(R.string.reset_suc))
    }

    override fun onResetFailed(error: String?) {
        viewBinding.forgotPass.showsnackBar(getString(R.string.res_Fail))
    }

}
