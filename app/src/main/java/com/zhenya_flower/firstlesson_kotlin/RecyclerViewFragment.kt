package com.zhenya_flower.firstlesson_kotlin

import adapter.ItemsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import listener.ItemsListener
import model.ItemsModel
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsAdapter = ItemsAdapter(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemsAdapter

        val format = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = format.format(Date())
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
        itemsAdapter.submitList(listItems.toList())
    }

    override fun onClick() {

    }

    override fun onItemSelected(name: String, date: String, imageView: Int) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()

        bundle.putString("name", name)
        bundle.putString("date", date)
        bundle.putInt("imageView", imageView)
        detailsFragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


}