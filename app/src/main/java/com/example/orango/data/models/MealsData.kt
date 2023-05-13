package com.example.orango.data.models

import com.example.orango.R

data class MealsData(val mealImage: Int , val mealName: String, val mealNumberOfItems: Int)

data class MealsProduct(val img: Int, val name: String, val price: String, val location: String)



private val buffaloSandwich = arrayListOf(
    MealsProduct(R.drawable.chicken_breast, "Chicken Breast", "135 LE", "Floor 2, Lane 5"),
    MealsProduct(R.drawable.hot_sauce, "Hot Sauce", "25 LE", "Floor 1, Lane 3"),
    MealsProduct(R.drawable.black_pepper, "Black Pepper", "15 LE", "Floor 1, Lane 4"),
    MealsProduct(R.drawable.butter, "Butter", "30 LE", "Floor 2, Lane 4"),
    MealsProduct(R.drawable.honey, "Honey", "140 LE", "Floor 1, Lane 3"),
    MealsProduct(R.drawable.ceaser_bread, "Ceaser Bread", "12 LE", "Floor 1, Lane 1")
)

private val chickenPizza = arrayListOf(
    MealsProduct(R.drawable.pizza_crust, "Pizza Crust", "47.5 LE", "Floor 1, Lane 1"),
    MealsProduct(R.drawable.pesto, "Pesto", "52 LE", "Floor 1, Lane 6"),
    MealsProduct(R.drawable.chicken_breast, "Chicken Breast", "135 LE", "Floor 2, Lane 5"),
    MealsProduct(R.drawable.marinated_artichoke_hearts, "Marinated Artichoke Hearts", "68 LE", "Floor 1, Lane 3"),
    MealsProduct(R.drawable.mozzarella_cheese, "Mozzarella Cheese", "38 LE", "Floor 2, Lane 4")
)

private val cranberryChicken = arrayListOf(
    MealsProduct(R.drawable.cranberry, "Cranberry", "85 LE", "Floor 1, Lane 6"),
    MealsProduct(R.drawable.water, "Water", "5 LE", "Floor 1, Lane 3"),
    MealsProduct(R.drawable.sugar, "Sugar", "13 LE", "Floor 1, Lane 4"),
    MealsProduct(R.drawable.chicken_breast, "Chicken Breast", "135 LE", "Floor 2, Lane 5"),
    MealsProduct(R.drawable.salt, "Salt", "3 LE", "Floor 1, Lane 4"),
    MealsProduct(R.drawable.black_pepper, "Black Pepper", "22 LE", "Floor 1, Lane 4"),
    MealsProduct(R.drawable.oil, "Oil", "60 LE", "Floor 1, Lane 2"),
    MealsProduct(R.drawable.maple_syrup, "Maple Syrup", "80 LE", "Floor 1, Lane 2")
)

private val grilledSteak = arrayListOf(
    MealsProduct(R.drawable.barbecue_sauce, "Barbecue Sauce", "35 LE", "Floor 1, Lane 2"),
    MealsProduct(R.drawable.beef_broth, "Beef Broth", "85 LE", "Floor 1, Lane 2"),
    MealsProduct(R.drawable.lemon_juice, "Lemon Juice", "30 LE", "Floor 1, Lane 2"),
    MealsProduct(R.drawable.beef_flank_steak, "Beef Flank Steak", "187 LE", "Floor 2, Lane 5"),
)

private val shrimpPasta = arrayListOf(
    MealsProduct(R.drawable.bow_tie_pasta, "Bow Tie Pasta", "22 LE", "Floor 1, Lane 4"),
    MealsProduct(R.drawable.peas, "Peas", "14 LE", "Floor 1, Lane 6"),
    MealsProduct(R.drawable.shrimp, "Shrimp", "250 LE", "Floor 2, Lane 5"),
    MealsProduct(R.drawable.alfredo_sauce, "Alfredo Sauce", "32 LE", "Floor 1, Lane 2"),
    MealsProduct(R.drawable.parmesan_cheese, "Parmesan Cheese", "76 LE", "Floor 2, Lane 3")
)



object Meals{


    private val images = arrayListOf(
        R.drawable.buffalo_chicken_sandwich, R.drawable.chicken_pesto_pizza, R.drawable.cranberry_maple_chicken
        , R.drawable.grilled_steak, R.drawable.shrimp_pasta
    )

    private val names = arrayListOf(
        "Buffalo Sandwich", "Chicken Pizza", "Cranberry Chicken", "Grilled Steak", "Shrimp Pasta"
    )

    private val items = arrayListOf(
        6, 5, 8, 4, 5
    )


    val mealIngredients = mapOf(names[0] to buffaloSandwich, names[1] to chickenPizza, names[2] to cranberryChicken,
        names[3] to grilledSteak, names[4] to shrimpPasta)

    // Returns the list of meals objects to be displayed in MealsListFragment RecyclerView
    var mealList: ArrayList<MealsData>? = null
        get() {

            if (field != null)      // backing 'field' refers to 'mealList' property object
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val names = names[i]
                val items = items[i]
                val meals = MealsData(imageId, names, items)
                field!!.add(meals)
            }

            return field
        }

}
