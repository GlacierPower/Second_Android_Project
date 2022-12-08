package domain

import model.ItemsModel

interface CarsRepository {
    fun getData():List<ItemsModel>
    }
