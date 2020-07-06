package com.vortex.soft.coctailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.coctailapp.domain.interactor.GetNoAlcoholics
import com.vortex.soft.coctailapp.domain.interactor.UseCase
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class NoAlcoholicsViewModel(private val getNoAlcoholics: GetNoAlcoholics) : BaseViewModel() {
    var drinks : MutableLiveData<List<DDrinkInfo>> = MutableLiveData()

    fun loadNoAlcoholics() = getNoAlcoholics(scope, UseCase.None()) { it.fold(::handleFailure, ::handleNoAlcoholicList)}

    private fun handleNoAlcoholicList(ddrinks : List<DDrinkInfo>) {
        this.drinks.value = ddrinks
    }
}