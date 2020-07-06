package com.vortex.soft.coctailapp.di.module

import com.vortex.soft.kotlinfirebasetest.domain.interactor.GetDrinkById
import com.vortex.soft.coctailapp.domain.interactor.GetAlcoholics
import com.vortex.soft.coctailapp.domain.interactor.GetCoctails
import com.vortex.soft.coctailapp.domain.interactor.GetNoAlcoholics
import com.vortex.soft.coctailapp.domain.interactor.GetOrdinaryDrinks
import com.vortex.soft.coctailapp.presentation.adapters.DrinkInfoAdapter
import com.vortex.soft.coctailapp.presentation.adapters.IngredientAdapter
import com.vortex.soft.coctailapp.presentation.ui.*
import com.vortex.soft.coctailapp.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    factory { GetOrdinaryDrinks(get()) }
    factory { GetCoctails(get()) }
    factory { GetAlcoholics(get()) }
    factory { GetNoAlcoholics(get()) }
    factory { GetDrinkById(get()) }

    viewModel { OrdinaryDrinkViewModel(get()) }
    viewModel { CoctailsViewModel(get()) }
    viewModel { AlcoholicsViewModel(get()) }
    viewModel { NoAlcoholicsViewModel(get()) }
    viewModel { DrinkViewModel(get()) }

    scope(named<OrdinaryDrinkFragment>()) {
        scoped { (clickListener: (String) -> Unit) -> DrinkInfoAdapter(clickListener) }
    }
    scope(named<CoctailsFragment>()) {
        scoped { (clickListener: (String) -> Unit) -> DrinkInfoAdapter(clickListener) }
    }
    scope(named<AlcoholicsFragment>()) {
        scoped { (clickListener: (String) -> Unit) -> DrinkInfoAdapter(clickListener) }
    }
    scope(named<NoAlcoholicsFragment>()) {
        scoped { (clickListener: (String) -> Unit) -> DrinkInfoAdapter(clickListener) }
    }
    scope(named<DrinkFragment>()) {
        scoped { IngredientAdapter() }
    }
}