package com.vortex.soft.coctailapp.data.repository

import com.vortex.soft.coctailapp.data.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface API {
    companion object {
        private const val ORDINARY_DRINK = "api/json/v1/1/filter.php?c=Ordinary_Drink"
        private const val COCKTAIL = "api/json/v1/1/filter.php?c=Cocktail"
        private const val ALCOGOLIC = "api/json/v1/1/filter.php?a=Alcoholic"
        private const val NO_ALCOGOLIC = "api/json/v1/1/filter.php?a=Non_Alcoholic"

        private const val DRINK = "api/json/v1/1/lookup.php"
    }

    @GET(ORDINARY_DRINK) fun getOrdinaryDrinks(): Call<DrinkInfos>
    @GET(COCKTAIL) fun getCoctails(): Call<DrinkInfos>
    @GET(ALCOGOLIC) fun getAlcogolics(): Call<DrinkInfos>
    @GET(NO_ALCOGOLIC) fun getNoAlcogolics(): Call<DrinkInfos>
    @GET(DRINK) fun getDrinkById(@Query("i") id: String?): Call<Drinks>
}