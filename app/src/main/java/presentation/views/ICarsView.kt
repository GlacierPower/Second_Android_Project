package presentation.views

import model.ItemsModel

interface ICarsView {

    fun dataReceive(list: List<ItemsModel>)

    fun addToFav(msg: Int)

    fun goToDetails(name: String, date: String, imageView: Int)
}