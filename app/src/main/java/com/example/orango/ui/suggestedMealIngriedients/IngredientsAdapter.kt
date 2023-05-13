package com.example.orango.ui.suggestedMealIngriedients

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orango.R
import com.example.orango.data.models.MealsProduct
import com.example.orango.databinding.CardProductBinding

class IngredientsAdapter (val context: Context, var ingredientsList: ArrayList<MealsProduct>): RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsViewHolder {
        Log.i("IngredientsAdapter" , "onCreateViewHolder : ViewHolder Created")

        val binding = CardProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(ingredientsViewHolder: IngredientsViewHolder, position: Int) {

        Log.i("IngredientsAdapter" , "onBindViewHolder : position $position")

        val meal = ingredientsList[position]
        ingredientsViewHolder.setData(meal, position)
    }
    override fun getItemCount(): Int = ingredientsList.size

    inner class IngredientsViewHolder(private val binding: CardProductBinding) : RecyclerView.ViewHolder(binding.root){

        private var currentPosition : Int = -1
        private var currentMeal : MealsProduct? = null

        private val imvIngredientImage = itemView.findViewById<ImageView>(R.id.product_img)
        private val txvIngredientName = itemView.findViewById<TextView>(R.id.product_name)
        private val txvIngredientPrice = itemView.findViewById<TextView>(R.id.price_after_dicount)
        private val txvIngredientLocation = itemView.findViewById<TextView>(R.id.product_location)

        init {
            binding.root.setOnClickListener {

            }
        }

        fun setData(meal: MealsProduct, position: Int){
            txvIngredientName.text = meal.name
            imvIngredientImage.setImageResource(meal.img)
            txvIngredientPrice.text = meal.price
            txvIngredientLocation.text = meal.location

            this.currentMeal = meal
            this.currentPosition = position
        }




    }

}
