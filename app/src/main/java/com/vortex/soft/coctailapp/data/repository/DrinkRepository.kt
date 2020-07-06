package com.vortex.soft.coctailapp.data.repository

import com.vortex.soft.coctailapp.data.repository.datastore.DrinkDataStoreFactory
import com.vortex.soft.coctailapp.domain.model.DDrink
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import com.vortex.soft.coctailapp.utils.functional.Either
import kotlinx.coroutines.flow.Flow

class DrinkRepository (private val factory: DrinkDataStoreFactory) : DrinkRepositoryInterface {
    override suspend fun getOrdinaryDrinks() : Flow<Either<Error, List<DDrinkInfo>>> = factory.retrieveRemoteDataStore().getOrdinaryDrinks()
    override suspend fun getCoctails() : Flow<Either<Error, List<DDrinkInfo>>> = factory.retrieveRemoteDataStore().getCoctails()
    override suspend fun getAlcogolics() : Flow<Either<Error, List<DDrinkInfo>>> = factory.retrieveRemoteDataStore().getAlcogolics()
    override suspend fun getNoAlcogolics() : Flow<Either<Error, List<DDrinkInfo>>> = factory.retrieveRemoteDataStore().getNoAlcogolics()
    override suspend fun getDrinkById(drinkId: String): Flow<Either<Error, DDrink>> = factory.retrieveRemoteDataStore().getDrinkById(drinkId)
}