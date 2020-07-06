package com.vortex.soft.coctailapp.presentation.ui

import android.os.Bundle
import android.view.View
import com.vortex.soft.coctailapp.R
import com.vortex.soft.coctailapp.data.repository.Error
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import com.vortex.soft.coctailapp.presentation.adapters.DrinkInfoAdapter
import com.vortex.soft.coctailapp.presentation.viewmodel.NoAlcoholicsViewModel
import com.vortex.soft.coctailapp.utils.extension.observe
import com.vortex.soft.coctailapp.utils.extension.toast
import com.vortex.soft.coctailapp.utils.extension.viewModel
import kotlinx.android.synthetic.main.fragment_no_alcoholics.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class NoAlcoholicsFragment : BaseFragment() {

    private val noAlcoholicsAdapter: DrinkInfoAdapter by lifecycleScope.inject{ parametersOf(::switchDrinkFragment) }
    private val noAlcoholicsViewModel: NoAlcoholicsViewModel by sharedViewModel()
    override fun layoutId() = R.layout.fragment_no_alcoholics

    //private lateinit var toolbarView: OrdinaryDrinkToolbarView
    //private var presenter: OrdinaryDrinkToolbarPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel(noAlcoholicsViewModel) {
            observe(drinks, ::renderDrinksList)
            observe(failure, ::renderError)
        }
        initializeView()
        loadDrinkList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //presenter?.unsubscribe()
    }

    private fun initializeView() {
        //initializeToolbarView()
        with(noAlcoholicList) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = noAlcoholicsAdapter
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
        noAlcoholicsAdapter.drinks = dusers.orEmpty()
        hideProgress()
        context?.toast(getString(R.string.drinks_load_success))
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    private fun loadDrinkList() {
        showProgress()
        noAlcoholicsViewModel.loadNoAlcoholics()
    }
}