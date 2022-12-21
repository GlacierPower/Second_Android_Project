package application.presentation

import application.model.User

interface AuthenticationPageListener {

    fun authenticateSuccess(user: User)
}