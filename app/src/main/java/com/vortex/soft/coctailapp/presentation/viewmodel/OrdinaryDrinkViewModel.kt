package com.vortex.soft.coctailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.coctailapp.domain.interactor.GetOrdinaryDrinks
import com.vortex.soft.coctailapp.domain.interactor.UseCase
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class OrdinaryDrinkViewModel(private val getOrdinaryDrinks : GetOrdinaryDrinks) : BaseViewModel() {
    var drinks : MutableLiveData<List<DDrinkInfo>> = MutableLiveData()

    fun loadOrdinaryDrinks() = getOrdinaryDrinks(scope, UseCase.None()) { it.fold(::handleFailure, ::handleOrdinaryDrinkList)}

    private fun handleOrdinaryDrinkList(ddrinks : List<DDrinkInfo>) {
        this.drinks.value = ddrinks
    }
}