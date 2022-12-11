package presentation

import data.repository.entity.User

interface AuthenticationPageListener {

    fun authenticateSuccess(user: User)
}