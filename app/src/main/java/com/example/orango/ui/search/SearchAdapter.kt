package com.example.orango.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.roomDB.entities.ProductEntity
import com.example.orango.R
import com.example.orango.databinding.CardProductBinding

class SearchAdapter(private val products : MutableList<ProductEntity>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: CardProductBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(product: ProductEntity) {
            binding.priceBeforeDicount.text = product.price.toString()
            binding.productName.text = product.productName
            binding.productLocation.text = product.location
            Glide.with(binding.root.context)
                .load(product.image)
                .centerCrop()
                .placeholder(R.drawable.tomato)
                .into(binding.productImg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val binding = CardProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    fun updateList(products: List<ProductEntity>){
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}