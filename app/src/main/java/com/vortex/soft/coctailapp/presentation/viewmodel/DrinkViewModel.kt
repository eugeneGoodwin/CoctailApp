package com.vortex.soft.coctailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.kotlinfirebasetest.domain.interactor.GetDrinkById
import com.vortex.soft.coctailapp.domain.model.DDrink
import kotlin.reflect.full.memberProperties

class DrinkViewModel (private val getDrinkById : GetDrinkById) : BaseViewModel() {
    var image : MutableLiveData<String> = MutableLiveData()
    var name : MutableLiveData<String> = MutableLiveData()
    var instruction : MutableLiveData<String> = MutableLiveData()
    var ingredients : MutableLiveData<List<Pair<String, String>>> = MutableLiveData()

    fun loadDrinkById(drinkId: String) = getDrinkById(scope, drinkId) { it.fold(::handleFailure, ::handleDrink)}

    private fun handleDrink(ddrinks : DDrink) {
        image.value = ddrinks.strDrinkThumb
        name.value = ddrinks.strDrink
        instruction.value = ddrinks.strInstructions
        var listIngredient: MutableList<Pair<String, String>> = mutableListOf()
        var mapMeasure: MutableMap<String, String> = mutableMapOf()
        ddrinks::class.memberProperties.filter{
                it.name.contains("Ingredient") || it.name.contains("Measure")
        }.forEach {
            val name: String = it.name
            val digit_name = it.name.replace("[^0-9]+".toRegex(),"")
            val body: String? = it.getter.call(ddrinks).toString()
            body?.let {
                if(!it.equals("null")) {
                    if(name.contains("Ingredient")) {
                        listIngredient.add(Pair(digit_name, it))
                    } else if(name.contains("Measure")) {
                        mapMeasure.put(digit_name, it)
                    }
                }
            }
        }
        listIngredient.sortWith(compareBy {
            "\\d+".toRegex().find(it.first)?.value?.toInt() ?: 0
        })
        ingredients.value = listIngredient.map {
            val measure = mapMeasure.get(it.first) ?: ""
            Pair(it.second, measure)
        }
    }
}