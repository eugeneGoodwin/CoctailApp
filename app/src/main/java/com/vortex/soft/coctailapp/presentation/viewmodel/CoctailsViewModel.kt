package com.vortex.soft.coctailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.coctailapp.domain.interactor.GetCoctails
import com.vortex.soft.coctailapp.domain.interactor.UseCase
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class CoctailsViewModel(private val getCoctails: GetCoctails) : BaseViewModel() {
    var drinks : MutableLiveData<List<DDrinkInfo>> = MutableLiveData()

    fun loadCoctails() = getCoctails(scope, UseCase.None()) { it.fold(::handleFailure, ::handleCoctailList)}

    private fun handleCoctailList(ddrinks : List<DDrinkInfo>) {
        this.drinks.value = ddrinks
    }
}