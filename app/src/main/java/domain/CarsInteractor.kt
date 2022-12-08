package domain

import model.ItemsModel

class CarsInteractor(private val carsRepository: CarsRepository) {
    fun getData(): List<ItemsModel> {
        return carsRepository.getData()
    }
}