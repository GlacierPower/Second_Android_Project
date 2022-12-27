package application.data

import androidx.fragment.app.FragmentManager
import application.model.User

interface AuthRepositoryCallBack {
    fun success(user: User)
    fun fail(error: String?)

}