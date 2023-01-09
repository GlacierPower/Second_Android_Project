package application.presentation.main_fragment

interface MainView {

    fun onLogoutSuccess(success :String)

    fun onLogoutFailed(error: String?)

    fun displayItemData(name: String, date: String, image: Int)

}