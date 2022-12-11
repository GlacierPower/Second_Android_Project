package data.repository

import androidx.fragment.app.FragmentManager
import data.repository.entity.User

interface AuthRepositoryCallBack {
    fun success(user: User)
    fun fail(error: String?)

}