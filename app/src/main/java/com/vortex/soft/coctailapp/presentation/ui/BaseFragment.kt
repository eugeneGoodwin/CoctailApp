package com.vortex.soft.coctailapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vortex.soft.coctailapp.utils.extension.toast
import kotlinx.android.synthetic.main.activity_main.*
import com.vortex.soft.coctailapp.data.repository.Error

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId(), container, false)


    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)


    private fun progressStatus(viewStatus: Int) {
        (activity as MainActivity).progressbar?.visibility = viewStatus
    }

    protected fun toastError(headerMessage: String, fail: Error?) {
        fail?.let { context?.toast(headerMessage + ": " + it.getMessage()) } ?: run { context?.toast(headerMessage) }
    }

    protected fun showError(fail: Error?) {
        when(fail) {
            is Error.NetworkConnectionError -> toastError("Network connection error", fail)
            is Error.UndefineError -> toastError("Undefine error", fail)
            is Error.ServerError -> toastError("Server error", fail)
            is Error -> toastError("Error", fail)
        }
    }

    fun switchDrinkFragment(idDrink: String){
        (activity as MainActivity).replaceFragment(DrinkFragment.newInstance(idDrink))
    }
}