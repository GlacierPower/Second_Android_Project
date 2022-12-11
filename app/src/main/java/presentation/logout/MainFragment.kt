package presentation.logout

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentMainBinding
import data.repository.repository.LogoutRepository
import presentation.AuthenticationPageListener
import presentation.login.LoginFragment
import untils.AppConstants.showsnackBar

class MainFragment : Fragment(), LogoutView {

    private lateinit var logoutPresenter: LogoutPresenter
    private lateinit var pageListener: AuthenticationPageListener

    private var _viewBinding: FragmentMainBinding? = null
    private val viewBinding get() = _viewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentMainBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logoutPresenter = LogoutPresenter(LogoutRepository(FirebaseAuth.getInstance()))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            logoutPresenter.logOut()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, LoginFragment())
                .commit()
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