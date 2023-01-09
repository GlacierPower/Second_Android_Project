package application.presentation.main_fragment

import application.domain.logout.MainFragmentInteractor
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(
    private val mainFragmentInteractor: MainFragmentInteractor
) {

    lateinit var mainView: MainView

    fun setView(mainFragment: MainFragment) {
        mainView = mainFragment
    }

    fun logOut() {
        mainFragmentInteractor.logOut(FirebaseAuth.getInstance())
        mainView.onLogoutSuccess("Success")
    }

    fun getArguments(name: String?, date: String?, imageView: Int) {

        mainView.displayItemData(
            when (name.isNullOrEmpty()) {
                true -> "No name"
                false -> name
            },
            when (date.isNullOrEmpty()) {
                true -> "No date"
                false -> date
            },

            imageView
        )
    }
}