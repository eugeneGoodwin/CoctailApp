package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.coctailapp.data.repository.DrinkRepositoryInterface
import com.vortex.soft.coctailapp.domain.interactor.UseCase
import com.vortex.soft.coctailapp.domain.model.DDrink

class GetDrinkById (private val repository: DrinkRepositoryInterface) : UseCase<DDrink, String>() {
    override suspend fun run(params: String) = repository.getDrinkById(params)
}