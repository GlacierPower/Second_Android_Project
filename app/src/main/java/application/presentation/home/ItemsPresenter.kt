package application.presentation.home

import android.util.Log
import application.domain.items.ItemsInteractor
import application.untils.AppConstants.EXCEPTION
import com.zhenya_flower.firstlesson_kotlin.R
import kotlinx.coroutines.*
import javax.inject.Inject

class ItemsPresenter @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) {

    private lateinit var itemsView: ItemsView

    fun setView(itemsFragment: ItemsFragment) {
        itemsView = itemsFragment
    }

    fun getItem() {
        val coroutinesExceptionHandler = CoroutineExceptionHandler { _, exceprion ->
            Log.w(EXCEPTION, exceprion.toString())
        }
        CoroutineScope(Dispatchers.Main + coroutinesExceptionHandler).launch {
            try {
                val items = itemsInteractor.getData()
                itemsView.itemReceived(items)
            } catch (e: Exception) {
                itemsView.getDataFail()
            }
            joinAll()
            cancel()
        }
    }

    fun imageViewClicked() {
        itemsView.imageClicked(R.string.image_was_clicked)
    }

    fun itemClicked(name: String, date: String, imageView: Int) {
        itemsView.itemClicked(NavigateWithBundle(name, date, imageView))
    }
}

data class NavigateWithBundle(
    val name: String,
    val date: String,
    val image: Int
)