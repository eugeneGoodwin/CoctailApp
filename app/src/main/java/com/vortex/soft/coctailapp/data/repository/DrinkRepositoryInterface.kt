package com.vortex.soft.coctailapp.data.repository

import com.vortex.soft.coctailapp.utils.functional.Either
import com.vortex.soft.coctailapp.domain.model.DDrink
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import kotlinx.coroutines.flow.Flow

interface DrinkRepositoryInterface {
    suspend fun getOrdinaryDrinks() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getCoctails() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getAlcogolics() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getNoAlcogolics() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getDrinkById(drinkId: String): Flow<Either<Error, DDrink>>
}