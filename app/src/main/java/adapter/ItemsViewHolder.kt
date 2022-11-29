package adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhenya_flower.firstlesson_kotlin.R
import listener.ItemsListener
import model.ItemsModel

class ItemsViewHolder(
    private val view: View,
    private val itemListener: ItemsListener
) : RecyclerView.ViewHolder(view) {
    fun bind(itemsModel: ItemsModel) {
        val name = view.findViewById<TextView>(R.id.recName)
        val date = view.findViewById<TextView>(R.id.recDate)
        val imageView = view.findViewById<ImageView>(R.id.recImage)
        val favImage = view.findViewById<ImageView>(R.id.recFavImage)

        name.text = itemsModel.name
        imageView.setBackgroundResource(itemsModel.image)
        date.text = itemsModel.date
        favImage.setBackgroundResource(itemsModel.favImage)

        favImage.setOnClickListener{
            if (itemsModel.favImage==R.drawable.first_star){
                favImage.setBackgroundResource(R.drawable.second_star)
            }else{
                favImage.setBackgroundResource(R.drawable.first_star)
            }
            itemListener.onClick()
        }

        itemView.setOnClickListener {
            itemListener.onItemSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }
    }
}