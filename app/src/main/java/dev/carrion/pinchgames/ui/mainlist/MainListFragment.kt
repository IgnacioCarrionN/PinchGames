package dev.carrion.pinchgames.ui.mainlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.carrion.pinchgames.R
import kotlinx.android.synthetic.main.fragment_main_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainListFragment : Fragment(), GameListAdapter.OnAdapterInteractions {


    private val viewModel: MainListViewModel by viewModel()

    private val adapter = GameListAdapter(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler(view)

        viewModel.gameList.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer {
            Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })

        swipeRefresh.setOnRefreshListener {
            viewModel.updateData()
        }
    }

    private fun initRecycler(view: View) {
        val recycler: RecyclerView = view.findViewById(R.id.recyclerMainList)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
    }

    override fun onItemClicked(id: Long) {
        val action = MainListFragmentDirections.actionMainListFragmentToGameDetailsFragment(id)
        findNavController().navigate(action)
    }
}