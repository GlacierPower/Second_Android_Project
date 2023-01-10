package application.presentation.main_fragment

import android.content.res.Resources
import android.util.Log
import application.domain.main_fragment.MainFragmentInteractor
import application.untils.AppConstants.EXCEPTION
import com.google.firebase.auth.FirebaseAuth
import com.zhenya_flower.firstlesson_kotlin.R
import kotlinx.coroutines.*
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(
    private val mainFragmentInteractor: MainFragmentInteractor
) {

    lateinit var mainView: MainView

    fun setView(mainFragment: MainFragment) {
        mainView = mainFragment
    }

    fun logOut() {
        val coroutinesExceptionHandler = CoroutineExceptionHandler { _, exceprion ->
            Log.w(EXCEPTION, exceprion.toString())
        }
        CoroutineScope(Dispatchers.Main + coroutinesExceptionHandler).launch {
            try {
                mainFragmentInteractor.logOut(FirebaseAuth.getInstance())
                mainView.onLogoutSuccess()
            } catch (e: Exception) {
                mainView.onLogoutFailed()
            }
            joinAll()
            cancel()
        }
    }

    fun getArguments(name: String?, date: String?, imageView: Int) {

        mainView.displayItemData(
            when (name.isNullOrEmpty()) {
                true -> Resources.getSystem().getString(R.string.no_name)
                false -> name
            },
            when (date.isNullOrEmpty()) {
                true -> Resources.getSystem().getString(R.string.no_date)
                false -> date
            },

            imageView
        )
    }
}