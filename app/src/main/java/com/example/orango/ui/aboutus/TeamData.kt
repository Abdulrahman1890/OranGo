package com.example.orango.ui.aboutus

import com.example.orango.R
import com.example.orango.data.models.Meals
import com.example.orango.data.models.MealsData

data class TeamData (val personImage: Int , val personName: String, val personRole: String)

object Team {


    private val images = arrayListOf(
        R.drawable.boody,
        R.drawable.ghanem,
        R.drawable.juba,
        R.drawable.taha,
        R.drawable.profile,
        R.drawable.profile,
        R.drawable.profile,
        R.drawable.profile
    )

    private val names = arrayListOf(
        "Abdulrahman Ragab",
        "Abdulrahman Ghanem",
        "Abdulrahman Moharam",
        "Taha Younes",
        "Sara El-Wakeel",
        "Howaida El-Hosary",
        "Nadine Mousa",
        "Alaa Atia"
    )

    private val roles = arrayListOf(
        "Android Developer", "Android Developer", "Data Analyst", "Embedded System Dev.", "UI/UX & Front-End Dev.",
        "AI & ML Developer", "AI & ML Developer", "Back-End Developer"
    )


    // Returns the list of people objects to be displayed in AboutUsFragment RecyclerView
    var peopleList: ArrayList<TeamData>? = null
        get() {

            if (field != null)      // backing 'field' refers to 'peopleList' property object
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val names = Team.names[i]
                val roles = Team.roles[i]
                val team = TeamData(imageId, names, roles)
                field!!.add(team)
            }

            return field
        }

}