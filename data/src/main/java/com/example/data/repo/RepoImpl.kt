package com.example.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.data.remote.Api
import com.example.data.roomDB.OranGoDataBase
import com.example.data.roomDB.entities.CategoryEntity
import com.example.data.roomDB.entities.ProductEntity
import com.example.data.roomDB.entities.ReceiptEntity
import com.example.data.roomDB.entities.asDatabaseModel
import com.example.domain.entity.json.AIResponse
import com.example.domain.entity.json.auth.logIn.CustomerData
import com.example.domain.entity.json.auth.logIn.User
import com.example.domain.entity.json.auth.signUp.Error
import com.example.domain.entity.json.auth.updateProfile.toUser
import com.example.domain.entity.json.feedback.AddFeedbackResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class RepoImpl(private val database: OranGoDataBase) {
    var customerData: CustomerData? = null
    var currentError: String? = null
    var user: User? = null
    private var signUpError: Error? = null

    val bestSellingProduct = database.orangoDao.getAllBestSelling()
    val bestSellingProductsTopFive = database.orangoDao.getSubBestSelling()
    val offerProductsTopFive = database.orangoDao.getSubOffers()
    val categoriesTopFive = database.orangoDao.getSubCategories()
    val favouriteProducts = database.orangoDao.getFavouriteProducts()
    val recommendedProducts = database.orangoDao.getRecommendedProduct()
    val offerProducts = database.orangoDao.getAllOffers()
    val categories = database.orangoDao.getAllCategories()

    suspend fun refreshCategories() {
        withContext(Dispatchers.IO) {
            val categoriesList = Api.retrofitService.getCategory()
            database.orangoDao.insertCategories(categoriesList.categories.asDatabaseModel())
        }
    }

    val sendFeedback: suspend (customerId: Int, message: String) -> AddFeedbackResponse =
        { customerId, message ->
            withContext(Dispatchers.IO) {
                Api.retrofitService.addFeedback(customerId, message)
            }
        }

    val getCategoryById: suspend (categoryId: Int) -> CategoryEntity? = { categoryId ->
        withContext(Dispatchers.IO) {
            database.orangoDao.getCategoryById(categoryId).getOrNull(0)
        }
    }

    val getNumberOfPoints: suspend (customerId: Int) -> Int = { customerId ->
        withContext(Dispatchers.IO) {
            Api.retrofitService.getPoints(customerId).points
        }
    }

    val getFavouriteProduct: suspend (customerId: Int) -> List<ProductEntity> = { customerId ->
        withContext(Dispatchers.IO) {
            Api.retrofitService.getFavouriteProducts(customerId = customerId).products.asDatabaseModel()
        }
    }

    val getProducts: suspend (id: Int) -> ProductEntity? = { id ->
        withContext(Dispatchers.IO) {
            database.orangoDao.getProductInfo(id).getOrNull(0)
        }
    }

    val searchProduct: suspend (name: String) -> LiveData<List<ProductEntity>> = { name ->
        withContext(Dispatchers.IO) {
            database.orangoDao.getSearchProduct(name)
        }
    }

    val getSimilarProducts: suspend (categoryId: Int) -> List<ProductEntity> =
        { categoryId ->
            withContext(Dispatchers.IO) {
                database.orangoDao.getSimilarProducts(categoryId)
            }
        }

    val detectProduct: suspend (imageFile: File) -> AIResponse = { imageFile ->
        withContext(Dispatchers.IO) {
            Api.retrofitServiceForAI.detectProduct(
                convertImageFileToMultimediaPart(imageFile)
            )
        }
    }

    val getProductByName: suspend (productName: String) -> ProductEntity? = { productName ->
        withContext(Dispatchers.IO) {
            database.orangoDao.getProductByName(productName).getOrNull(0)
        }
    }

    val getReceiptHistory: suspend (customerId: Int) -> List<ReceiptEntity> = { customerId ->
        withContext(Dispatchers.IO) {
            Api.retrofitService.getCustomerReceipt(customerId).receipts.asDatabaseModel()
        }
    }
        suspend fun refreshFavourites(customerId: Int) {
                withContext(Dispatchers.IO) {
                    val favouriteProductsResponse =
                        Api.retrofitService.getFavouriteProducts(customerId = customerId)
                    withContext(Dispatchers.Main) {
                        //favoritesLiveData.value =
                            favouriteProductsResponse.products.asDatabaseModel()
                    }
                }
            }
        suspend fun refreshProducts(customerId: Int) {
            withContext(Dispatchers.IO) {
                val productsList = Api.retrofitService.getAllProducts(customerId = customerId)
                database.orangoDao.addProduct(productsList.products.asDatabaseModel())
            }
        }


        val products: LiveData<List<ProductEntity>> = database.orangoDao.getProducts()


        suspend fun updateFavorites(customerId: Int, product: ProductEntity) {
            withContext(Dispatchers.IO) {
                database.orangoDao.setProductFavouriteState(product)
                if (product.liked == 1) Api.retrofitService.insertToFavourite(
                    customerId,
                    product.id
                )
                else Api.retrofitService.deleteFromFavourite(customerId, product.id)
            }

        }

        suspend fun clearUserDB() {
            withContext(Dispatchers.IO) {
                database.clearAllTables()
            }
        }

        suspend fun logIn(email: String, password: String): Boolean {
            return withContext(Dispatchers.IO) {
                val logInResponse = Api.retrofitService.logIn(email, password)
                Log.d("TAGGG", "logIn: ${logInResponse}")
                logInResponse.customerData?.let { customerData ->
                    this@RepoImpl.customerData = customerData
                }
                logInResponse.error?.let { error ->
                    currentError = error
                }
                return@withContext logInResponse.status
            }
        }

        suspend fun signUp(
            username: String,
            email: String,
            phoneNumber: String,
            password: String
        ): Boolean {
            return withContext(Dispatchers.IO) {
                val signUpResponse =
                    Api.retrofitService.signUp(username, email, phoneNumber, password)
                signUpResponse.error?.let { error ->
                    signUpError = error
                }
                return@withContext signUpResponse.status
            }
        }

        suspend fun updateProfile(
            id: Int,
            username: String,
            email: String,
            phoneNumber: String,
            password: String,
            imageUri: String
        ) {
            withContext(Dispatchers.IO) {
                val idRequestBody = id.toString().toRequestBody(MultipartBody.FORM)
                val usernameRequestBody = username.toRequestBody(MultipartBody.FORM)
                val emailRequestBody = email.toRequestBody(MultipartBody.FORM)
                val phoneNumberRequestBody = phoneNumber.toRequestBody(MultipartBody.FORM)
                val passwordRequestBody = password.toRequestBody(MultipartBody.FORM)
                val imagePart = if (imageUri.isNotBlank()) {
                    val file = File(imageUri)
                    val imageRequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    imageRequestBody.let {
                        MultipartBody.Part.createFormData("image", file.name, it)
                    }
                } else
                    null

                val updateProfileResponse = Api.retrofitService.updateProfile(
                    idRequestBody,
                    usernameRequestBody,
                    emailRequestBody,
                    phoneNumberRequestBody,
                    passwordRequestBody,
                    imagePart
                )

                Log.d("TAGGG", "updateProfile: $updateProfileResponse")
                updateProfileResponse.customerData?.let { customerData ->
                    this@RepoImpl.user = customerData.toUser()
                }
                return@withContext updateProfileResponse.status
            }
        }

        suspend fun getProductByCategoryId(categoryId: Int) =
            withContext(Dispatchers.IO) {
                try {
                    Api.retrofitService.getProductOfCategory(categoryId).products.asDatabaseModel()
                } catch (ex: Exception) {
                    try {
                        database.orangoDao.getProductsByCategoryId(categoryId)
                    } catch (ex: Exception) {
                        listOf()
                    }
                }
            }

        fun String.toRequestBody(contentType: MediaType): RequestBody {
            return RequestBody.create(contentType, this)
        }

        fun File.asRequestBody(mediaType: MediaType?): RequestBody {
            return RequestBody.create(mediaType, this)
        }

        private fun convertImageFileToMultimediaPart(imageFile: File): MultipartBody.Part {
            // Create a RequestBody object with the image file
            val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)

            // Create a MultipartBody.Part using the RequestBody
            return MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
        }
}
