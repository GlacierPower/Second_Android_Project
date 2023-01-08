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
    private val itemListener: ItemsListener
) : RecyclerView.ViewHolder(view) {
    fun bind(itemsModel: ItemsModel) {
        val name = view.findViewById<TextView>(R.id.tvName)
        val date = view.findViewById<TextView>(R.id.tvDate)
        val imageView = view.findViewById<ImageView>(R.id.ivPicture)

        name.text = itemsModel.name
        imageView.setBackgroundResource(itemsModel.image)
        date.text = itemsModel.date

        imageView.setOnClickListener {
            itemListener.onClick()
        }
        itemView.setOnClickListener {
            itemListener.onElementSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }
    }

}