package com.example.orango.ui.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orango.R
import com.example.orango.data.models.Meals
import com.example.orango.ui.suggestedMeals.MealsAdapter

class AboutUsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)
        setupRecyclerView(view)
        return view
    }
    private fun setupRecyclerView(view: View?) {
        val context = requireContext()
        val teamAdapter = TeamAdapter(context, Team.peopleList!!)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.aboutUsList)
        recyclerView?.adapter = teamAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }
}