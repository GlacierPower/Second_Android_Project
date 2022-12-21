package application.presentation.forgot_pass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import application.data.forgot_pass.ForgotPasswordRepositoryImpl
import application.domain.forgot_pass.ForgotPassInteractor
import application.model.User
import application.presentation.login.LoginFragment
import application.untils.AppConstants.showsnackBar
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentForgotPassBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPassFragment : Fragment(), application.presentation.forgot_pass.ForgotPassView {

    private var _viewBinding: FragmentForgotPassBinding? = null
    private val viewBinding get() = _viewBinding

    @Inject
    lateinit var forgotPassPresenter: ForgotPassPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentForgotPassBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        forgotPassPresenter = ForgotPassPresenter(
            ForgotPassInteractor(ForgotPasswordRepositoryImpl())
        )

        val user = User(
            email = viewBinding?.emailForfot?.text.toString().trim()
        )
        viewBinding?.btnForgot?.setOnClickListener {
            forgotPassPresenter.doReset(user)
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, LoginFragment())
                .commit()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onEmailEmpty() {
        view?.showsnackBar("Email can't bee empty")
    }

    override fun onEmailInvalid() {
        view?.showsnackBar("Invalid email")
    }

    override fun onResetSuccess(user: User) {
        TODO("Not yet implemented")
    }

    override fun onProgress(visibility: Int) {
        TODO("Not yet implemented")
    }

    override fun onResetFailed(error: String?) {
        TODO("Not yet implemented")
    }

}
