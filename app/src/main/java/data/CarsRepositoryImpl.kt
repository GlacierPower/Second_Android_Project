package data

import com.zhenya_flower.firstlesson_kotlin.R
import domain.CarsRepository
import model.ItemsModel
import java.text.SimpleDateFormat
import java.util.*

class CarsRepositoryImpl : CarsRepository {
    override fun getData(): List<ItemsModel> {
        val format = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = format.format(Date())
        val carsList = listOf<ItemsModel>(
            ItemsModel(R.drawable.acura, "CDX", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.cadil, "Escalade", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.chev, "Corvet", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.honda, "Civic", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.hyn, "Accent", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.lada, "Vesta", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.lexus, "LX", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.mabyh, "Exelero", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.mazda, "6", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.reno, "Laguna", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.rover, "75", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.shcoda, "Octavia", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.subaru, "Impreza", currentDate, R.drawable.first_star),
            ItemsModel(R.drawable.volk, "Polo", currentDate, R.drawable.first_star)
        )
        return carsList
    }
}