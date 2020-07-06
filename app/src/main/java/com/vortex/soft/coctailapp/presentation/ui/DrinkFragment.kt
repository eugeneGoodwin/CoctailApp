package com.vortex.soft.coctailapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vortex.soft.coctailapp.R
import com.vortex.soft.coctailapp.data.repository.Error
import com.vortex.soft.coctailapp.presentation.adapters.IngredientAdapter
import com.vortex.soft.coctailapp.presentation.viewmodel.DrinkViewModel
import com.vortex.soft.coctailapp.utils.extension.observe
import com.vortex.soft.coctailapp.utils.extension.putArgs
import com.vortex.soft.coctailapp.utils.extension.toast
import com.vortex.soft.coctailapp.utils.extension.viewModel
import kotlinx.android.synthetic.main.fragment_drink.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DrinkFragment : BaseFragment() {

    private val drinkAdapter: IngredientAdapter by lifecycleScope.inject()
    private val drinkViewModel: DrinkViewModel by sharedViewModel()
    override fun layoutId() = R.layout.fragment_drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        val idDrink: String? = arguments?.getString("drinkKey")
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idDrink: String? = arguments?.getString("drinkKey")
        viewModel(drinkViewModel) {
            observe(image, ::renderImage)
            observe(name, ::renderName)
            observe(instruction, ::renderInstruction)
            observe(ingredients, ::renderIngredients)
            observe(failure, ::renderError)
        }
        initializeView()
        idDrink?.let { loadDrink(it) }
    }

    private fun initializeView() {
        with(ingredientList) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = drinkAdapter
        }
    }

    private fun renderImage(image: String?) {
        Glide.with(drink_info_image).load(image).into(drink_info_image)
    }

    private fun renderName(name: String?) {
        drink_info_name.text = name
    }

    private fun renderInstruction(instruction: String?) {
        drink_info_instruction.text = instruction
    }

    private fun renderIngredients(ingredients: List<Pair<String, String>>?) {
        drinkAdapter.drinks = ingredients.orEmpty()
        drinkAdapter.notifyDataSetChanged()
        hideProgress()
        context?.toast(getString(R.string.drink_load_success))
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    private fun loadDrink(idDrink: String) {
        showProgress()
        drinkViewModel.loadDrinkById(idDrink)
    }

    companion object {
        fun newInstance(idDrink: String) = DrinkFragment().putArgs {
            putString("drinkKey", idDrink)
        }
    }
}