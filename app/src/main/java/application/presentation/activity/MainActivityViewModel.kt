package application.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import application.domain.main_activiry.MainActivityInteractor
import application.domain.main_activiry.MainActivityRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainActivityInteractor: MainActivityInteractor
) : ViewModel() {

    private val _nav = MutableLiveData<FirebaseAuth>()
    val nav: LiveData<FirebaseAuth> = _nav

    fun checkExists() {
        _nav.value = mainActivityInteractor.userExists()
    }
}
