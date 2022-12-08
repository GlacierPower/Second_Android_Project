package presentation.presenters

import com.zhenya_flower.firstlesson_kotlin.R
import domain.CarsInteractor
import presentation.views.ICarsView

class CarsPresenter(
    private val ICarsView: ICarsView,
    private val carsInteractor: CarsInteractor
) {

    fun getData() {
        val carsList = carsInteractor.getData()
        ICarsView.dataReceive(carsList)
    }

    fun addToFavour() {
        ICarsView.addToFav(R.string.favorite)
    }

    fun elementSelected(name: String, date: String, image: Int){
        ICarsView.goToDetails(name,date,image)
    }

}