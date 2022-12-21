package application.presentation.registration


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import application.data.registration.RegisterRepositoryImpl
import application.domain.login.LoginInteractor
import application.domain.login.LoginRepository
import application.domain.registration.RegistrationInteractor
import application.model.User
import application.presentation.login.LoginFragment
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment(), RegisterView {


    private lateinit var pageListener: application.presentation.AuthenticationPageListener
    @Inject
    lateinit var registerPresenter: RegisterPresenter


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

        registerPresenter = RegisterPresenter(
            RegistrationInteractor(RegisterRepositoryImpl()))

        viewBinding?.btnRegistration?.setOnClickListener {
            val user = User(
                email = viewBinding?.emailRegister?.text.toString().trim(),
                username = viewBinding?.userNameRegister?.text.toString().trim(),
                password = viewBinding?.passwordRegister?.text.toString().trim(),
                confirmPassword = viewBinding?.confirmPass?.text.toString().trim()
            )
            registerPresenter.doRegister(user)
            replaceFragment(parentFragmentManager,LoginFragment(),true)

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






