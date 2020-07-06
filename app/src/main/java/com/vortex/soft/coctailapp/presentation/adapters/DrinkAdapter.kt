package com.vortex.soft.coctailapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vortex.soft.coctailapp.R
import kotlinx.android.synthetic.main.drink_layout.view.*
import kotlin.properties.Delegates

class DrinkAdapter() : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    internal var drinks: List<Pair<String, String>> by Delegates.observable(emptyList()) {
            _,_,_ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.drink_layout, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(drinks[position])
    }

    override fun getItemCount() = drinks.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drinkProperty: Pair<String, String>) {
            with(itemView) {
                drink_name.text = drinkProperty.first
                drink_body.text = drinkProperty.second
            }
        }
    }
}