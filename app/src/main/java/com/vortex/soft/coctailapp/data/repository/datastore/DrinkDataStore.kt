package com.vortex.soft.coctailapp.data.repository.datastore

import com.vortex.soft.coctailapp.data.repository.Error
import com.vortex.soft.coctailapp.domain.model.DDrink
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import com.vortex.soft.coctailapp.utils.functional.Either
import kotlinx.coroutines.flow.Flow

interface DrinkDataStore {
    suspend fun getOrdinaryDrinks() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getCoctails() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getAlcogolics() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getNoAlcogolics() : Flow<Either<Error, List<DDrinkInfo>>>
    suspend fun getDrinkById(drinkId: String): Flow<Either<Error, DDrink>>
}