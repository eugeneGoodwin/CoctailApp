package com.vortex.soft.coctailapp.data.repository.datastore

open class DrinkDataStoreFactory (private val remoteDrinkDataStore: DrinkDataStore) {
    open fun retrieveRemoteDataStore(): DrinkDataStore = remoteDrinkDataStore
}