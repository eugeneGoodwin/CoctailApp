package com.vortex.soft.coctailapp.domain.interactor

import com.vortex.soft.coctailapp.data.repository.DrinkRepositoryInterface
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class GetAlcoholics (private val repository: DrinkRepositoryInterface) : UseCase<List<DDrinkInfo>,UseCase.None>() {
    override suspend fun run(params: UseCase.None) = repository.getAlcogolics()
}