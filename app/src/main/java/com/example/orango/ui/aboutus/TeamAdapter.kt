package com.example.orango.ui.aboutus

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.orango.R
import com.example.orango.databinding.ItemListAboutUsBinding
import com.example.orango.ui.suggestedMeals.SuggestedMealsFragmentDirections
import com.google.android.material.imageview.ShapeableImageView

class TeamAdapter(val context: Context, var peopleList: ArrayList<TeamData>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamViewHolder {
        Log.i("TeamAdapter" , "onCreateViewHolder : ViewHolder Created")

        val binding = ItemListAboutUsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(teamViewHolder: TeamViewHolder, position: Int) {

        Log.i("TeamAdapter" , "onBindViewHolder : position $position")

        val team = peopleList[position]
        teamViewHolder.setData(team, position)
    }

    override fun getItemCount(): Int = peopleList.size

    inner class TeamViewHolder(private val binding : ItemListAboutUsBinding) : RecyclerView.ViewHolder(binding.root){

        private var currentPosition : Int = -1
        private var currentPerson : TeamData? = null

        private val txvPersonName = itemView.findViewById<TextView>(R.id.personName)
        private val imvPersonImage = itemView.findViewById<ImageView>(R.id.personImg)
        private val txvPersonRole = itemView.findViewById<TextView>(R.id.personRole)

        fun setData(person: TeamData, position: Int){
            txvPersonName.text = person.personName
            imvPersonImage.setImageResource(person.personImage)
            txvPersonRole.text = person.personRole

            this.currentPerson = person
            this.currentPosition = position
        }




    }

}
