package application.data.items

import application.domain.items.ItemsRepository
import application.model.ItemsModel
import com.zhenya_flower.firstlesson_kotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor() : ItemsRepository {
    override suspend fun getData(): List<ItemsModel> = withContext(Dispatchers.IO) {
        return@withContext listOf(
            ItemsModel(
                "Novak Djokovic",
                R.drawable.novak,
                "22.05.1987"
            ),
            ItemsModel(
                "Roger Federer",
                R.drawable.roger,
                "08.08.1981"
            ),
            ItemsModel(
                "Rafael Nadal",
                R.drawable.nadal,
                "03.06.1986"
            ),
            ItemsModel(
                "Holger Rune",
                R.drawable.rune,
                "29.04.2003"
            ),
            ItemsModel(
                "Victoria Azarenka",
                R.drawable.azarenka,
                "31.07.1989"
            ),
            ItemsModel(
                "Paula Badosa",
                R.drawable.badosa,
                "15.11.1997"
            ),
            ItemsModel(
                "Simona Halep",
                R.drawable.halep,
                "27.09.1991"
            ),
            ItemsModel(
                "Casper Ruud",
                R.drawable.ruud,
                "22.12.1998"
            ),
            ItemsModel(
                "Carlos Alcaraz",
                R.drawable.alcaraz,
                "05.05.2003"
            ),
            ItemsModel(
                "Aleksander Zverev",
                R.drawable.zverev,
                "20.04.1997"
            ),
            ItemsModel(
                "Jannik Sinner",
                R.drawable.sinner,
                "16.08.2001"
            ),
            ItemsModel(
                "Matteo Berrettini",
                R.drawable.berretini,
                "12.04.1996"
            ),
            ItemsModel(
                "Daniil Medvedev",
                R.drawable.medvedev,
                "11.02.1996"
            )
        )
    }

}

