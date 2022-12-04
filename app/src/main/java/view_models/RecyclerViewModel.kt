package view_models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhenya_flower.firstlesson_kotlin.R
import model.ItemsModel
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewModel : ViewModel() {
    private val _cars = MutableLiveData<List<ItemsModel>>()
    val car: LiveData<List<ItemsModel>> = _cars

    private val _bundle = MutableLiveData<BundleNavigation>()
    val bundle: LiveData<BundleNavigation> = _bundle

    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private val currentDate = format.format(Date())

    fun getData() {
        val listItems = listOf<ItemsModel>(
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
        _cars.value = listItems
    }

    fun bundleNavi(name: String, date: String, imageView: Int) {
        _bundle.value = BundleNavigation(
            name = name,
            date = date,
            image = imageView
        )

    }

}

data class BundleNavigation(
    val image: Int,
    val name: String,
    val date: String

)
