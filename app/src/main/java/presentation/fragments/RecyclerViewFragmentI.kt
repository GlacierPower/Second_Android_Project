package presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentRecyclerViewBinding
import data.CarsRepositoryImpl
import domain.CarsInteractor
import model.ItemsModel
import presentation.adapter.ItemsAdapter
import presentation.adapter.listener.ItemsListener
import presentation.presenters.CarsPresenter
import presentation.views.ICarsView
import untils.AppConstants.DATE
import untils.AppConstants.IMAGE_VIEW
import untils.AppConstants.NAME

class RecyclerViewFragmentI : Fragment(), ItemsListener, ICarsView {

    private var _viewBinding : FragmentRecyclerViewBinding?=null
    private val viewBinding get() = _viewBinding!!

    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var carsPresenter: CarsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentRecyclerViewBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsAdapter = ItemsAdapter(this)
        carsPresenter = CarsPresenter(this, CarsInteractor(CarsRepositoryImpl()))

        viewBinding.recyclerView.adapter = itemsAdapter


        carsPresenter.getData()
    }

    override fun onClick() {
        carsPresenter.addToFavour()
    }

    override fun onItemSelected(name: String, date: String, imageView: Int) {
        carsPresenter.elementSelected(name, date, imageView)
    }

    override fun dataReceive(list: List<ItemsModel>) {
        itemsAdapter.submitList(list)
    }

    override fun addToFav(msg: Int) {
        Toast.makeText(context,getString(msg),Toast.LENGTH_SHORT).show()
    }

    override fun goToDetails(name: String, date: String, imageView: Int) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()

        bundle.putString(NAME, name)
        bundle.putString(DATE, date)
        bundle.putInt(IMAGE_VIEW, imageView)
        detailsFragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


}