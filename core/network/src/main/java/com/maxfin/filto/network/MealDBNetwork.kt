package com.maxfin.filto.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxfin.filto.network.base.BaseNetwork
import com.maxfin.filto.network.dto.MealFilterRequestArgs
import com.maxfin.filto.network.dto.MealResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface MealDBService {

    @GET(value = "search.php")
    suspend fun searchMealByName(
        @Query("s") query: String,
    ): Response<MealResponse>


    @GET(value = "filter.php")
    suspend fun filterMeal(
        @Query("i") ingredient: String?,
        @Query("c") category: String?,
        @Query("a") area: String?,
    ): Response<MealResponse>

    @GET(value = "random.php")
    suspend fun randomMeal(): Response<MealResponse>

    @GET(value = "lookup.php")
    suspend fun detailsMeal(
        @Query("i") id: String,
    ): Response<MealResponse>
}

@Singleton
class MealDBNetwork @Inject constructor(okHttpClient: OkHttpClient, networkJson: Json) :
    BaseNetwork(), MealDBDataSource {

    private val networkApi = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(MealDBService::class.java)

    override suspend fun searchMealByName(query: String) =
        getResult {
            networkApi.searchMealByName(query)
        }

    override suspend fun filterMeal(
        mealFilterArgs: MealFilterRequestArgs
    ) =
        getResult {
            networkApi.filterMeal(
                mealFilterArgs.ingredient,
                mealFilterArgs.category,
                mealFilterArgs.area
            )
        }

    override suspend fun randomMeal() =
        getResult {
        networkApi.randomMeal()
            }


    override suspend fun detailsMeal(id: String) =
        getResult {
            networkApi.detailsMeal(id)
        }


}
