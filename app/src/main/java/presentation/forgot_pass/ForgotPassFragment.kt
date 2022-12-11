package presentation.forgot_pass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentForgotPassBinding
import data.repository.entity.User
import presentation.login.LoginFragment
import data.repository.repository.ForgotPasswordRepository
import untils.AppConstants.showsnackBar

class ForgotPassFragment : Fragment(), ForgotPassView {

    private var _viewBinding: FragmentForgotPassBinding? = null
    private val viewBinding get() = _viewBinding

    private lateinit var forgotPassPresenter: ForgotPassPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentForgotPassBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        forgotPassPresenter = ForgotPassPresenter(ForgotPasswordRepository(FirebaseAuth.getInstance()))

        val user = User(
            email = viewBinding?.emailForfot?.text.toString().trim()
        )
        viewBinding?.btnForgot?.setOnClickListener { forgotPassPresenter.doReset(user)
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
