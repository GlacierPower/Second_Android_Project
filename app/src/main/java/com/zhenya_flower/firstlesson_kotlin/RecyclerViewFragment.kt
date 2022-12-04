package com.zhenya_flower.firstlesson_kotlin

import adapter.ItemsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import listener.ItemsListener

import view_models.RecyclerViewModel


class RecyclerViewFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter

    private val viewModel: RecyclerViewModel by viewModels()

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

        viewModel.getData()
        viewModel.car.observe(viewLifecycleOwner) { carsList ->
            itemsAdapter.submitList(carsList)
        }

        viewModel.bundle.observe(viewLifecycleOwner) { bundleNav ->
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()

            bundle.putString("name", bundleNav.name)
            bundle.putString("date", bundleNav.date)
            bundle.putInt("imageView", bundleNav.image)
            detailsFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onClick() {}

    override fun onItemSelected(name: String, date: String, imageView: Int) {
        viewModel.bundleNavi(name, date, imageView)
    }
}