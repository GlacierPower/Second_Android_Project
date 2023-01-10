package application.presentation.main_fragment

interface MainView {

    fun onLogoutSuccess()

    fun onLogoutFailed()

    fun displayItemData(name: String, date: String, image: Int)



}