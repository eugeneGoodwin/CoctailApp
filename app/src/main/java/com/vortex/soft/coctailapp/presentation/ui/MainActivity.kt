package com.vortex.soft.coctailapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.vortex.soft.coctailapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import com.vortex.soft.coctailapp.data.repository.Error
import com.vortex.soft.coctailapp.utils.extension.toast

class MainActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_ordinary_drink -> {
                    toast(getString(R.string.ordinary_drink))
                    replaceFragment(OrdinaryDrinkFragment())
                }
                R.id.action_cocktail -> {
                    toast(getString(R.string.cocktail))
                    replaceFragment(CoctailsFragment())
                }
                R.id.action_alcoholic -> {
                    toast(getString(R.string.alcoholic))
                    replaceFragment(AlcoholicsFragment())
                }
                R.id.action_no_alcoholic -> {
                    toast(getString(R.string.no_alcoholic))
                    replaceFragment(NoAlcoholicsFragment())
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        replaceFragment(OrdinaryDrinkFragment())
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }
}
