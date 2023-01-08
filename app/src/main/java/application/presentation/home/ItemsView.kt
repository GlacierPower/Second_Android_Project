package application.presentation.home

import application.model.ItemsModel

interface ItemsView {
    fun itemReceived(items: List<ItemsModel>)

    fun imageClicked(message: Int)

    fun itemClicked(bundleNavigation: NavigateWithBundle)
}