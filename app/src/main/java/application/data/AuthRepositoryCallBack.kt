package application.data

import application.model.User

interface AuthRepositoryCallBack {
    fun success(user: User)
    fun fail(error: String?)

}