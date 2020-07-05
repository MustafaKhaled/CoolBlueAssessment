package com.khaleds.coolblue.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.khaleds.coolblue.MainActivity
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.di.components.DaggerDataComponent
import com.khaleds.coolblue.presentation.di.component.DaggerPresentationComponent
import com.khaleds.coolblue.presentation.factory.ViewModelFactory
import com.khaleds.coolblue.presentation.viewmodels.AllProductsViewModel
import com.khaleds.coolblue.ui.home.di.component.DaggerAllProductsComponent
import com.khaleds.coolblue.util.MyApplication
import com.khaleds.coolblue.util.StateUi
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
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
        allProductsViewModel.observeState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is StateUi.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is StateUi.Success -> {
                    progressBar.visibility = View.GONE

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

                return true
            }
        })
        searchView.setOnClickListener { view -> }
    }


}