package application.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import application.domain.items.ItemsInteractor
import application.model.ItemsModel
import com.zhenya_flower.firstlesson_kotlin.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) : ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    fun getData() {
        _items.value = itemsInteractor.getDate()
    }

    fun imageViewClicked() {
        _msg.value = R.string.image_view
    }

    fun elementClicked(name: String, imageView: Int, date: String) {
        _bundle.value = NavigateWithBundle(
            name = name,
            image = imageView,
            date = date
        )
    }

    fun userNavigated() {
        _bundle.value = null
    }
}

data class NavigateWithBundle(
    val name: String,
    val image: Int,
    val date: String
)