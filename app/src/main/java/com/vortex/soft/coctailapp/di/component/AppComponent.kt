package com.vortex.soft.coctailapp.di.component

import com.vortex.soft.coctailapp.di.module.networkModule
import com.vortex.soft.coctailapp.di.module.repositoryModule
import com.vortex.soft.coctailapp.di.module.viewModelModule

val appComponent = listOf(networkModule, repositoryModule, viewModelModule)