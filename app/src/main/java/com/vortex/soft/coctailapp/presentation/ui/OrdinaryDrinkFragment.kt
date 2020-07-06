package com.vortex.soft.coctailapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import com.vortex.soft.coctailapp.R
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import com.vortex.soft.coctailapp.presentation.adapters.DrinkInfoAdapter
import com.vortex.soft.coctailapp.presentation.viewmodel.OrdinaryDrinkViewModel
import com.vortex.soft.coctailapp.utils.extension.observe
import com.vortex.soft.coctailapp.utils.extension.toast
import com.vortex.soft.coctailapp.utils.extension.viewModel
import kotlinx.android.synthetic.main.fragment_ordinary_drink.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.vortex.soft.coctailapp.data.repository.Error
import org.koin.core.parameter.parametersOf

class OrdinaryDrinkFragment : BaseFragment() {

    private val drinksAdapter: DrinkInfoAdapter by lifecycleScope.inject{ parametersOf(::switchDrinkFragment)}
    private val drinksViewModel: OrdinaryDrinkViewModel by sharedViewModel()
    override fun layoutId() = R.layout.fragment_ordinary_drink

    //private lateinit var toolbarView: OrdinaryDrinkToolbarView
    //private var presenter: OrdinaryDrinkToolbarPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel(drinksViewModel) {
            observe(drinks, ::renderDrinksList)
            observe(failure, ::renderError)
        }
        initializeView()
        loadDrinksList()
        ordinaryDrinkMotionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                Log.i("KotlinFlowTest????????????????????????????????????????","onTransitionCompleted")
            }
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                Log.i("KotlinFlowTest???????????????????????????????????","onTransitionStarted")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //presenter?.unsubscribe()
    }

    private fun initializeView() {
        //initializeToolbarView()
        with(ordinaryDrinkList) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = drinksAdapter
        }
    }

    /*private fun initializeToolbarView() {
        toolbarView = UsersToolbarView.inflate(context!!)
        toolbarView.setTitle(getString(R.string.users))
        presenter = UsersToolbarPresenter(toolbarView, this)
        presenter?.subscribe()
        with(activity as MainActivity){ switchToolbar(toolbarView) }
    }*/

    private fun renderDrinksList(dusers: List<DDrinkInfo>?) {
        drinksAdapter.drinks = dusers.orEmpty()
        hideProgress()
        context?.toast(getString(R.string.drinks_load_success))
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    private fun loadDrinksList() {
        showProgress()
        drinksViewModel.loadOrdinaryDrinks()
    }
}