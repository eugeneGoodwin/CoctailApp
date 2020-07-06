package com.vortex.soft.coctailapp.data.repository.datastore.remote

import com.vortex.soft.coctailapp.data.mapper.DrinkInfoMapper
import com.vortex.soft.coctailapp.data.mapper.DrinkMapper
import com.vortex.soft.coctailapp.data.model.DrinkInfos
import com.vortex.soft.coctailapp.data.model.Drinks
import com.vortex.soft.coctailapp.utils.functional.Either
import com.vortex.soft.coctailapp.data.repository.Error
import com.vortex.soft.coctailapp.data.repository.NetService
import com.vortex.soft.coctailapp.data.repository.NetworkHandler
import com.vortex.soft.coctailapp.data.repository.NetworkRepository
import com.vortex.soft.coctailapp.data.repository.datastore.DrinkDataStore
import com.vortex.soft.coctailapp.domain.model.DDrink
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DrinkRemoteDataStoreImpl(private val networkHandler: NetworkHandler,
                               private val service: NetService,
                               private val mapper: DrinkInfoMapper,
                               private val mapper2: DrinkMapper
) : NetworkRepository(), DrinkDataStore {

    override suspend fun getOrdinaryDrinks(): Flow<Either<com.vortex.soft.coctailapp.data.repository.Error, List<DDrinkInfo>>> {
        return flow {
            emit( when (networkHandler.isConnected) {
                true -> request<DrinkInfos, List<DDrinkInfo>>(service.getOrdinaryDrinks(), { it.drinks.map{ mapper.mapFromEntity(it) }.toList() }, DrinkInfos(emptyList()))
                false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
            } )
        }.catch { Either.Left(Error.UndefineError(it.message ?: "Undefine error")) }
    }

    override suspend fun getCoctails(): Flow<Either<Error, List<DDrinkInfo>>> {
        return flow {
            emit( when (networkHandler.isConnected) {
                true -> request<DrinkInfos, List<DDrinkInfo>>(service.getCoctails(), { it.drinks.map{ mapper.mapFromEntity(it) }.toList() }, DrinkInfos(emptyList()))
                false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
            } )
        }.catch { Either.Left(Error.UndefineError(it.message ?: "Undefine error")) }
    }

    override suspend fun getAlcogolics(): Flow<Either<Error, List<DDrinkInfo>>> {
        return flow {
            emit( when (networkHandler.isConnected) {
                true -> request<DrinkInfos, List<DDrinkInfo>>(service.getAlcogolics(), { it.drinks.map{ mapper.mapFromEntity(it) }.toList() }, DrinkInfos(emptyList()))
                false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
            } )
        }.catch { Either.Left(Error.UndefineError(it.message ?: "Undefine error")) }
    }

    override suspend fun getNoAlcogolics(): Flow<Either<Error, List<DDrinkInfo>>> {
        return flow {
            emit( when (networkHandler.isConnected) {
                true -> request<DrinkInfos, List<DDrinkInfo>>(service.getNoAlcogolics(), { it.drinks.map{ mapper.mapFromEntity(it) }.toList() }, DrinkInfos(emptyList()))
                false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
            } )
        }.catch { Either.Left(Error.UndefineError(it.message ?: "Undefine error")) }
    }

    override suspend fun getDrinkById(drinkId: String): Flow<Either<Error, DDrink>> {
        return flow {
            emit( when (networkHandler.isConnected) {
                true -> request<Drinks, DDrink>(service.getDrinkById(drinkId), { mapper2.mapFromEntity(it.drinks[0]) }, Drinks(emptyList()))
                false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
            } )
        }.catch { Either.Left(Error.UndefineError(it.message ?: "Undefine error")) }
    }
}