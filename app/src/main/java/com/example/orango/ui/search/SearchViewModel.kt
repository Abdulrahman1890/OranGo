package com.example.orango.ui.search

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.repo.RepoImpl
import com.example.data.roomDB.OranGoDataBase
import com.example.data.roomDB.entities.ProductEntity
import com.example.domain.entity.json.auth.logIn.CustomerData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel (application: Application) : AndroidViewModel(application) {

    private val database = OranGoDataBase.getInstance(application.applicationContext)
    private val repo = RepoImpl(database)
    private val productsLiveData = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> = productsLiveData


    private val sharedPreferences by lazy {
        application.applicationContext.getSharedPreferences(
            "my_shared_preferences",
            Context.MODE_PRIVATE
        )
    }

    init {
        viewModelScope.launch {
            launch {
                try {
                    val customerDataJson = sharedPreferences.getString("customer_data", null)
                    val savedCustomerData =
                        Gson().fromJson(customerDataJson, CustomerData::class.java)
                    Log.d("logIn", "onViewCreated: $savedCustomerData")
                 repo.refreshProducts(savedCustomerData!!.user.id)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }


    fun search(search: String){
        viewModelScope.launch {
            productsLiveData.value = repo.searchProduct(search).value ?: mutableListOf()
        }
    }

}