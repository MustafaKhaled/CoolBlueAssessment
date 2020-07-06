package com.khaleds.coolblue.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.di.components.DaggerDataComponent
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.presentation.di.component.DaggerPresentationComponent
import com.khaleds.coolblue.presentation.factory.ViewModelFactory
import com.khaleds.coolblue.presentation.viewmodels.ProductDetailsViewModel
import com.khaleds.coolblue.ui.details.di.component.DaggerProductDetailsComponent
import com.khaleds.coolblue.ui.home.di.component.DaggerAllProductsComponent
import com.khaleds.coolblue.util.MyApplication
import javax.inject.Inject

class ProductDetailsFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var product: Product
    lateinit var productDetailsViewModel: ProductDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindArguments(arguments)
        DaggerProductDetailsComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(
                        DaggerDataComponent.builder()
                            .applicationComponent(MyApplication.applicationComponent).build()
                    )
                    .build()
            ).build().inject(this)
        productDetailsViewModel = ViewModelProvider(this,viewModelFactory).get(ProductDetailsViewModel::class.java)
        product.productId?.let { productDetailsViewModel.getDetails(it) }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindArguments(arguments: Bundle?) {
        if(arguments!=null){
            product = ProductDetailsFragmentArgs.fromBundle(arguments).product
        }
    }
}