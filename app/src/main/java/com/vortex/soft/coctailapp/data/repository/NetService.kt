package com.vortex.soft.coctailapp.data.repository

import com.vortex.soft.coctailapp.data.model.DrinkInfos
import retrofit2.Call
import retrofit2.Retrofit

class NetService (val retrofit: Retrofit) : API{
    private val api:API by lazy { retrofit.create(API::class.java) }

    override fun getOrdinaryDrinks(): Call<DrinkInfos> = api.getOrdinaryDrinks()
    override fun getCoctails() = api.getCoctails()
    override fun getAlcogolics() = api.getAlcogolics()
    override fun getNoAlcogolics() = api.getNoAlcogolics()
    override fun getDrinkById(id: String?) = api.getDrinkById(id)
}