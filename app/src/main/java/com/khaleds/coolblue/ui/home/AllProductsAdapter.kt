package com.khaleds.coolblue.ui.home

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.remote.entities.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_item.view.*

class AllProductsAdapter(private val clickListener: (Product) -> Unit): RecyclerView.Adapter<AllProductsAdapter.ProductViewHolder>() {
    private val mainList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(mainList[holder.adapterPosition],clickListener)
    }

    fun addAll(allProducts: List<Product>?){
        allProducts?.let { mainList.addAll(it) }
        allProducts?.size?.let { notifyItemRangeInserted(mainList.size, it) }
    }

    class ProductViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(singleProduct: Product, clickListener: (Product) -> Unit) = with(itemView){
            singleProduct.let {
                Picasso.get().load(it.productImage).into(productImage)
                productName.text = it.productName
                price.text = it.salesPriceIncVat.toString().plus("€")
                totalReviews.text = it.reviewInformation?.reviewSummary?.reviewCount.toString().plus(" ").plus(context.getString(
                                    R.string.reviewers_label))
                productRating.rating = ((it.reviewInformation?.reviewSummary?.reviewAverage!!)/2).toFloat()
                if(it.nextDayDelivery==null && it.nextDayDelivery==false) sameDayLinear.visibility = View.GONE
                itemView.setOnClickListener {
                    clickListener(singleProduct)
                }
            }
            
        }
    }
}