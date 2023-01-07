package application.domain.items

import application.model.ItemsModel

interface ItemsRepository {
    fun getData(): List<ItemsModel>
}