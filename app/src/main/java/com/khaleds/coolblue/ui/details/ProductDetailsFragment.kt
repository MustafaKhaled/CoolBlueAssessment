package com.khaleds.coolblue.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.remote.entities.Product

class ProductDetailsFragment: Fragment() {
    lateinit var product: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindArguments(arguments)
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