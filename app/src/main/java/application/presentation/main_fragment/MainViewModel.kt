package application.presentation.main_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import application.domain.logout.LogoutRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val logoutRepository: LogoutRepository
) : ViewModel() {

    private val _nav = MutableLiveData<Unit>()
    val nav: LiveData<Unit> = _nav

    fun logOut(auth: FirebaseAuth) {
        logoutRepository.logOut(auth)
        _nav.value = Unit
    }
}