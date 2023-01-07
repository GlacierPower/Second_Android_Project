package application.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import application.model.ItemsModel
import application.presentation.adapter.listener.ItemsListener
import com.zhenya_flower.firstlesson_kotlin.R

class ItemsViewHolder(
    private val view: View,
    private val itemsListener: ItemsListener
) : RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel) {

        val name = view.findViewById<TextView>(R.id.tvName)
        val date = view.findViewById<TextView>(R.id.tvDate)
        val imageView = view.findViewById<ImageView>(R.id.ivPicture)

        name.text = itemsModel.name
        date.text = itemsModel.date
        imageView.setBackgroundResource(itemsModel.image)

        imageView.setOnClickListener {
            itemsListener.onClick()
        }

        itemView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }
    }

}