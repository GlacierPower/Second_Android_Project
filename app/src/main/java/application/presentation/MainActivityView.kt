package application.presentation

import com.google.firebase.auth.FirebaseAuth

interface MainActivityView {

fun userLogged(auth: FirebaseAuth)
}