package com.khaleds.coolblue.ui.details

// simplifies adding media
// simplifies adding media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.di.components.DaggerDataComponent
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.presentation.di.component.DaggerPresentationComponent
import com.khaleds.coolblue.presentation.factory.ViewModelFactory
import com.khaleds.coolblue.presentation.viewmodels.ProductDetailsViewModel
import com.khaleds.coolblue.ui.details.di.component.DaggerProductDetailsComponent
import com.khaleds.coolblue.util.MyApplication
import com.khaleds.coolblue.util.StateUi
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.fragment_product_details.progressBar
import kotlinx.android.synthetic.main.single_item.view.*
import java.lang.StringBuilder
import javax.inject.Inject


class ProductDetailsFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var product: Product
    lateinit var productDetailsViewModel: ProductDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindArguments(arguments)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = product.productName
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
        productDetailsViewModel.uiState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is StateUi.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is StateUi.Success -> {
                    progressBar.visibility = View.GONE
                    val result = it.data as ProductDetailsResponse
                    setupSlider(result.product.productImages)
                    setUpViews(result.product)
                }

                is StateUi.Error -> {
                    progressBar.visibility = View.GONE

                }

            }

        })
    }

    private fun bindArguments(arguments: Bundle?) {
        if(arguments!=null){
            product = ProductDetailsFragmentArgs.fromBundle(arguments).product
        }
    }

    private fun setupSlider(urls: List<String>){
        val imageList = ArrayList<SlideModel>()
        for(url in urls){
            imageList.add(SlideModel(url, ScaleTypes.CENTER_INSIDE))
        }
        imageSlider.setImageList(imageList)
    }

    private fun setUpViews(product: Product){
        //Price TextView
        price.text = product.salesPriceIncVat.toString().plus("€")
        //Total Reviewers
        totalReviews.text = product.reviewInformation?.reviewSummary?.reviewCount.toString().plus(" ").plus(context?.getString(
            R.string.reviewers_label))
        //RatingBar
        productRating.rating = ((product.reviewInformation?.reviewSummary?.reviewAverage!!)/2).toFloat()
        if(product.nextDayDelivery==null && product.nextDayDelivery==false) sameDayLinear.visibility = View.GONE

        //Description TextView
        descriptionValue.text = product.productText

        //Pros TextView
        val prosBuilder = StringBuilder()
        product.pros?.forEach { prosBuilder.append(it).append("\n")  }
        prosValue.text = prosBuilder.toString()

        //Cons TextView
        val consBuilder = StringBuilder()
        product.cons?.forEach { consBuilder.append(it).append("\n")  }
        consValue.text = consBuilder.toString()


    }

}