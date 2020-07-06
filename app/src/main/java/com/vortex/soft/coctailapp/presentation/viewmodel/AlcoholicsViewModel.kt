package com.vortex.soft.coctailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.coctailapp.domain.interactor.GetAlcoholics
import com.vortex.soft.coctailapp.domain.interactor.UseCase
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class AlcoholicsViewModel(private val getAlcoholics: GetAlcoholics) : BaseViewModel() {
    var drinks : MutableLiveData<List<DDrinkInfo>> = MutableLiveData()

    fun loadAlcoholics() = getAlcoholics(scope, UseCase.None()) { it.fold(::handleFailure, ::handleAlcoholicList)}

    private fun handleAlcoholicList(ddrinks : List<DDrinkInfo>) {
        this.drinks.value = ddrinks
    }
}