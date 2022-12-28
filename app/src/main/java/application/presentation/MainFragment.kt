package application.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import application.data.logout.LogoutRepositoryImpl
import application.domain.logout.LogoutInteractor
import application.presentation.login.LoginFragment
import application.presentation.logout.LogoutPresenter
import application.presentation.logout.LogoutView
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), LogoutView {

    private lateinit var logoutPresenter: LogoutPresenter

    private var _viewBinding: FragmentMainBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        val mUser = auth.currentUser
        viewBinding.tvEmail.text = mUser?.email
        viewBinding.tvEmailVerifiied.text = mUser?.isEmailVerified.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMainBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logoutPresenter = LogoutPresenter(
            LogoutInteractor(
                LogoutRepositoryImpl()
            )
        )
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            logoutPresenter.logOut()
            replaceFragment(
                parentFragmentManager,
                LoginFragment(),
                false
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLogoutSuccess(success: String) {
        view?.showsnackBar(getString(R.string.logout_succ))
    }

    override fun onLogoutFailed(error: String?) {
        view?.showsnackBar(getString(R.string.logout_failed))
    }

}