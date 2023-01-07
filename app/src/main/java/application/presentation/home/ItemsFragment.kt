package application.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import application.model.ItemsModel
import application.presentation.adapter.ItemsAdapter
import application.presentation.adapter.listener.ItemsListener
import application.presentation.main_fragment.MainFragment
import application.untils.AppConstants.showsnackBar
import application.untils.BundleConstance.DATE
import application.untils.BundleConstance.IMAGE_VIEW
import application.untils.BundleConstance.NAME
import application.untils.NavigationOnFragment.replaceFragment
import com.google.android.material.snackbar.Snackbar
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentItemsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener {

    lateinit var playersRV: RecyclerView
    private lateinit var itemsAdapter: ItemsAdapter
    lateinit var itemsList: ArrayList<ItemsModel>

    private val viewModel: ItemsViewModel by viewModels()

    private var _viewBinding: FragmentItemsBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentItemsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playersRV = viewBinding.recyclerView
        itemsAdapter = ItemsAdapter(this)
        itemsList = ArrayList()

        itemsAdapter.notifyDataSetChanged()

        viewBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerView.adapter = itemsAdapter

        viewModel.getData()
        viewModel.items.observe(viewLifecycleOwner) { listItems ->
            itemsAdapter.submitList(listItems)

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val deletePlayer: ItemsModel = listItems[viewHolder.adapterPosition]
                    val position = viewHolder.adapterPosition

                    itemsList = listItems.toList() as ArrayList<ItemsModel>
                    itemsList.removeAt(viewHolder.adapterPosition)
                    itemsAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                    Snackbar.make(
                        playersRV,
                        "Delete"
                                + deletePlayer.name,
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(
                            "Undo",
                            View.OnClickListener {
                                itemsList.add(position, deletePlayer)
                                itemsAdapter.notifyItemInserted(position)
                            }).show()
                }

            }).attachToRecyclerView(playersRV)

        }

        viewModel.msg.observe(viewLifecycleOwner) { msg ->
            view.showsnackBar(getString(R.string.was_clicked))
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val mainFragment = MainFragment()
                val bundle = Bundle()

                bundle.putString(NAME, navBundle.name)
                bundle.putString(DATE, navBundle.date)
                bundle.putInt(IMAGE_VIEW, navBundle.image)
                mainFragment.arguments = bundle

                replaceFragment(
                    parentFragmentManager,
                    mainFragment,
                    true
                )
                viewModel.userNavigated()
            }

        }
    }

    override fun onClick() {
        viewModel.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        viewModel.elementClicked(name, imageView, date)
    }


}