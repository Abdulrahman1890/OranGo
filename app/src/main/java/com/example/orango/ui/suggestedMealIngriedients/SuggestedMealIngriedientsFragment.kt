package com.example.orango.ui.suggestedMealIngriedients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orango.R
import com.example.orango.data.models.Meals
import com.example.orango.data.models.MealsProduct
import com.example.orango.databinding.FragmentSignUpBinding
import com.example.orango.databinding.FragmentSuggestedMealIngriedientsBinding
import com.example.orango.ui.suggestedMeals.MealsAdapter

class SuggestedMealIngriedientsFragment : Fragment() {

    private val viewModel: MealIngriedientsViewModel by viewModels()
    private var _binding: FragmentSuggestedMealIngriedientsBinding? = null
    private val binding get() = _binding!!

    lateinit var listOfIngredients : ArrayList<MealsProduct>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuggestedMealIngriedientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val mealName = arguments?.getString("mealName")

       val meal = Meals.mealList?.find{
            it.mealName == mealName
        }

        meal?.let {
            binding.includeMeal.mealImg.setImageResource(meal.mealImage)
            binding.includeMeal.mealName.text = meal.mealName
            binding.includeMeal.noOfItems.text = meal.mealNumberOfItems.toString()

        }

        listOfIngredients = Meals.mealIngredients.get(mealName)?: arrayListOf()
        setupRecyclerView(binding.root)
    }

    private fun setupRecyclerView(view: View?) {
        val context = requireContext()
        val ingredientsAdapter = IngredientsAdapter(context,listOfIngredients)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.productRecycleView)
        recyclerView?.adapter = ingredientsAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }

}