package com.khaleds.coolblue.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.khaleds.coolblue.R
import com.khaleds.coolblue.data.remote.entities.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_item.view.*

class AllProductsAdapter(private val clickListener: (Product) -> Unit): RecyclerView.Adapter<AllProductsAdapter.ProductViewHolder>(), Filterable {
    private val originalList = ArrayList<Product>()
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
        allProducts?.let { mainList.addAll(it)
        copy(mainList)
        }
        allProducts?.size?.let { notifyItemRangeInserted(mainList.size, it) }
    }

    private fun copy(originalList: List<Product>){
        this.originalList.addAll(originalList)
    }

    fun getOriginalList(): List<Product>{
        return originalList
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



    override fun getFilter(): Filter {
        return filterResult
    }
    private val filterResult: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Product> = ArrayList()
            if (constraint.isEmpty()) {
                mainList.clear()
                mainList.addAll(originalList)
                notifyDataSetChanged()
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in mainList) {
                    if (item.productName?.toLowerCase()?.contains(filterPattern)!!) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            mainList.clear()
            mainList.addAll(results.values as List<Product>)
            notifyDataSetChanged()
        }
    }
}