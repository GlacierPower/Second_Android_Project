package application.presentation

import application.domain.login.LoginInteractor
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    loginInteractor: LoginInteractor
) {
    lateinit var mainActivityView: MainActivityView

    fun setView(mainActivity: MainActivity) {
        mainActivityView = mainActivity
    }
}