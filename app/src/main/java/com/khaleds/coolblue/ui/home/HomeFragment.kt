package com.khaleds.coolblue.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaleds.coolblue.MainActivity
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.di.components.DaggerDataComponent
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.presentation.di.component.DaggerPresentationComponent
import com.khaleds.coolblue.presentation.factory.ViewModelFactory
import com.khaleds.coolblue.presentation.viewmodels.AllProductsViewModel
import com.khaleds.coolblue.ui.home.di.component.DaggerAllProductsComponent
import com.khaleds.coolblue.util.MyApplication
import com.khaleds.coolblue.util.StateUi
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var adapter: AllProductsAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var allProductsViewModel: AllProductsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        DaggerAllProductsComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(
                        DaggerDataComponent.builder()
                            .applicationComponent(MyApplication.applicationComponent).build()
                    )
                    .build()
            ).build().inject(this)

        allProductsViewModel =
            ViewModelProvider(this, viewModelFactory).get(AllProductsViewModel::class.java)
        allProductsViewModel.getAllProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        allProductsViewModel.observeState()
        allProductsViewModel.observeState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is StateUi.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is StateUi.Success -> {
                    progressBar.visibility = View.GONE
                    val result = it.data as ProductsResponse
                    adapter.addAll(result.products)
                }

                is StateUi.Error -> {
                    progressBar.visibility = View.GONE

                }

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        searchView.isIconified = false
        menu.findItem(R.id.action_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter.filter.filter(query);
                return true
            }
        })
        searchView.setOnClickListener { view -> }
    }

    private fun setupRecyclerView() {
        adapter = AllProductsAdapter { product: Product -> movieItemClicked(product) }
        layoutManager = GridLayoutManager(context,2, LinearLayoutManager.VERTICAL, false)
        productList.layoutManager = layoutManager
        productList.adapter = adapter
    }

    private fun movieItemClicked(product: Product) {
        val action = product.productId?.let { HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(productId = it) }!!
        Navigation.findNavController(productList).navigate(action)
    }

}