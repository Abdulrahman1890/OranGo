package com.example.orango.ui.suggestedMeals

import android.annotation.SuppressLint
import android.content.Context
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.orango.R
import com.example.orango.data.models.MealsData
import com.example.orango.databinding.CardMealIngrediantsBinding
import com.example.orango.ui.suggestedMealIngriedients.SuggestedMealIngriedientsFragment
import com.example.orango.ui.suggestedMealIngriedients.SuggestedMealIngriedientsFragmentDirections
import com.google.android.material.imageview.ShapeableImageView

class MealsAdapter(val context: Context, var mealsList: ArrayList<MealsData>): RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealsViewHolder {
        Log.i("MealsAdapter" , "onCreateViewHolder : ViewHolder Created")

        val binding = CardMealIngrediantsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(mealsViewHolder: MealsViewHolder, position: Int) {

        Log.i("MealsAdapter" , "onBindViewHolder : position $position")

        val meal = mealsList[position]
        mealsViewHolder.setData(meal, position)
    }

    override fun getItemCount(): Int = mealsList.size

    inner class MealsViewHolder(private val binding : CardMealIngrediantsBinding) : RecyclerView.ViewHolder(binding.root){

        private var currentPosition : Int = -1
        private var currentMeal : MealsData? = null

        private val txvMealName = itemView.findViewById<TextView>(R.id.meal_name)
        private val imvMealImage = itemView.findViewById<ShapeableImageView>(R.id.meal_img)
        private val txvIngredientsNumber = itemView.findViewById<TextView>(R.id.no_of_items)

        init {
            binding.root.setOnClickListener {
                val action = SuggestedMealsFragmentDirections.actionSuggestedMealsFragmentToSuggestedMealIngriedientsFragment(txvMealName.text.toString())
                binding.root.findNavController().navigate(action)
            }
        }

        @SuppressLint("SetTextI18n")
        fun setData(meal: MealsData, position: Int){
            txvMealName.text = meal.mealName
            imvMealImage.setImageResource(meal.mealImage)
            txvIngredientsNumber.text = meal.mealNumberOfItems.toString() + " Items"

            this.currentMeal = meal
            this.currentPosition = position
        }




    }

}
