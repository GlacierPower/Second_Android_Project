package presentation.registration


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentRegistrationBinding
import data.repository.entity.User
import data.repository.repository.RegisterRepository
import presentation.AuthenticationPageListener
import presentation.logout.MainFragment
import untils.AppConstants.showsnackBar


class RegistrationFragment : Fragment(), RegisterView {

    private lateinit var pageListener: AuthenticationPageListener
    private lateinit var registerPresenter: RegisterPresenter


    private var _viewBinding: FragmentRegistrationBinding? = null
    private val viewBinding get() = _viewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentRegistrationBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerPresenter = RegisterPresenter(RegisterRepository(FirebaseAuth.getInstance()))

        viewBinding?.btnRegistration?.setOnClickListener {
            val user = User(
                email = viewBinding?.emailRegister?.text.toString().trim(),
                username = viewBinding?.userNameRegister?.text.toString().trim(),
                password = viewBinding?.passwordRegister?.text.toString().trim(),
                confirmPassword = viewBinding?.confirmPass?.text.toString().trim()
            )
            registerPresenter.doRegister(user)

        }

    }

    override fun checkingData() {
        requireView().showsnackBar("message")
    }


    override fun onRegisterSuccess(user: User) {
        pageListener.authenticateSuccess(user)
    }

    override fun onRegisterFailed(error: String?) {
        view?.showsnackBar(getString(R.string.ref_fail))
    }

}






