package application.presentation.home

import application.domain.items.ItemsInteractor
import com.zhenya_flower.firstlesson_kotlin.R
import javax.inject.Inject

class ItemsPresenter @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) {

    private lateinit var itemsView: ItemsView

    fun setView(itemsFragment: ItemsFragment) {
        itemsView = itemsFragment
    }

    fun getItem() {
        val items = itemsInteractor.getData()
        itemsView.itemReceived(items)
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