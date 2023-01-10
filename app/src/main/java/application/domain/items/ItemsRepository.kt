package application.domain.items

import application.model.ItemsModel

interface ItemsRepository {
   suspend fun getData(): List<ItemsModel>
}