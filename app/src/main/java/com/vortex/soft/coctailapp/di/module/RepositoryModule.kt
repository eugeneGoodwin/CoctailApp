package com.vortex.soft.coctailapp.di.module

import com.vortex.soft.coctailapp.data.mapper.DrinkInfoMapper
import com.vortex.soft.coctailapp.data.mapper.DrinkMapper
import com.vortex.soft.coctailapp.data.repository.DrinkRepository
import com.vortex.soft.coctailapp.data.repository.DrinkRepositoryInterface
import com.vortex.soft.coctailapp.data.repository.NetworkHandler
import com.vortex.soft.coctailapp.data.repository.datastore.DrinkDataStore
import com.vortex.soft.coctailapp.data.repository.datastore.DrinkDataStoreFactory
import com.vortex.soft.coctailapp.data.repository.datastore.remote.DrinkRemoteDataStoreImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    factory { NetworkHandler(androidApplication()) }

    factory <DrinkDataStore> { DrinkRemoteDataStoreImpl(get(), get(), mapper = DrinkInfoMapper(), mapper2 = DrinkMapper()) }

    factory { DrinkDataStoreFactory(get()) }

    factory <DrinkRepositoryInterface> { DrinkRepository(get()) }
}