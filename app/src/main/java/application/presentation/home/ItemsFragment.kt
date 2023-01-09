package application.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import application.model.ItemsModel
import application.presentation.main_fragment.MainFragment
import application.presentation.adapter.ItemsAdapter
import application.presentation.adapter.listener.ItemsListener
import application.untils.AppConstants.DATE
import application.untils.AppConstants.IMAGE_VIEW
import application.untils.AppConstants.NAME
import application.untils.AppConstants.showsnackBar
import application.untils.NavigationOnFragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentItemsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener, ItemsView {

    private var _viewBinding: FragmentItemsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var itemsAdapter: ItemsAdapter

    @Inject
    lateinit var itemsPresenter: ItemsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentItemsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter.setView(this)
        itemsAdapter = ItemsAdapter(this)

        viewBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerView.adapter = itemsAdapter

        itemsPresenter.getItem()
    }

    override fun itemReceived(items: List<ItemsModel>) {
        itemsAdapter.submitList(items)
    }

    override fun imageClicked(message: Int) {
        view?.showsnackBar(getString(R.string.iv_clicked))
    }

    override fun itemClicked(bundleNavigation: NavigateWithBundle) {
        val mainFragment = MainFragment()
        val bundle = Bundle()

        bundle.putString(NAME, bundleNavigation.name)
        bundle.putString(DATE, bundleNavigation.date)
        bundle.putInt(IMAGE_VIEW, bundleNavigation.image)
        mainFragment.arguments = bundle

        NavigationOnFragment.replaceFragment(
            parentFragmentManager,
            mainFragment,
            true
        )
    }

    override fun onClick() {
        itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.itemClicked(name, date, imageView)
    }


}