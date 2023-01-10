package application.presentation.main_fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import application.presentation.login.LoginFragment
import application.untils.AppConstants.DATE
import application.untils.AppConstants.IMAGE_VIEW
import application.untils.AppConstants.NAME
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment.replaceFragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), MainView {

    @Inject
    lateinit var mainFragmentPresenter: MainFragmentPresenter

    private var _viewBinding: FragmentMainBinding? = null
    private val viewBinding get() = _viewBinding!!


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
        super.onViewCreated(view, savedInstanceState)

        mainFragmentPresenter.setView(this)

        val bundle = arguments
        bundle?.let { safeBundle ->
            mainFragmentPresenter.getArguments(
                safeBundle.getString(NAME),
                safeBundle.getString(DATE),
                safeBundle.getInt(IMAGE_VIEW)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            mainFragmentPresenter.logOut()
            replaceFragment(
                parentFragmentManager,
                LoginFragment(),
                false
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLogoutSuccess() {
        view?.showsnackBar(getString(R.string.logout_succ))
    }

    override fun onLogoutFailed() {
        view?.showsnackBar(getString(R.string.logout_failed))
    }

    override fun displayItemData(name: String, date: String, image: Int) {
        viewBinding.nameTV.text = name
        viewBinding.dateTV.text = date
        viewBinding.detailIV.setBackgroundResource(image)
    }

}