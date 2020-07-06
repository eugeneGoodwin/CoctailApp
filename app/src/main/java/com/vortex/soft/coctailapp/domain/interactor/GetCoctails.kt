package com.vortex.soft.coctailapp.domain.interactor

import com.vortex.soft.coctailapp.data.repository.DrinkRepositoryInterface
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class GetCoctails (private val repository: DrinkRepositoryInterface) : UseCase<List<DDrinkInfo>,UseCase.None>() {
    override suspend fun run(params: None) = repository.getCoctails()
}