package presentation.login

import data.repository.AuthRepositoryCallBack
import data.repository.entity.User
import data.repository.repository.LoginRepository

class LoginPresenter(private val loginRepository: LoginRepository) {
    private var view: LoginView? = null

    fun doLogin(user: User) {

        if (user.email?.isEmpty() == true) {
            view?.onEmailEmpty()
            return
        }


        if (user.password?.isEmpty() == true) {
            view?.onPasswordEmpty()
            return
        }


        loginRepository.doLogin(user, object : AuthRepositoryCallBack {
            override fun success(user: User) {
            }

            override fun fail(error: String?) {
                view?.onLoginFailed(error)

            }


        })
    }
}