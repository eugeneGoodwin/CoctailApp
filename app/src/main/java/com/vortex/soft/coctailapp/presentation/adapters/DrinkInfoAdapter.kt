package com.vortex.soft.coctailapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vortex.soft.coctailapp.R
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo
import kotlinx.android.synthetic.main.drink_info_layout.view.*
import kotlin.properties.Delegates

class DrinkInfoAdapter(val clickListener: (String) -> Unit) : RecyclerView.Adapter<DrinkInfoAdapter.ViewHolder>() {

    internal var drinks: List<DDrinkInfo> by Delegates.observable(emptyList()) {
            _,_,_ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.drink_info_layout_2, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(drinks[position], clickListener)
    }

    override fun getItemCount() = drinks.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drinkInfo: DDrinkInfo, clickListener: (String) -> Unit) {
            with(itemView) {
                drink_title.text = drinkInfo.strDrink
                Glide.with(drink_image).load(drinkInfo.strDrinkThumb).into(drink_image)
                setOnClickListener { clickListener(drinkInfo.idDrink) }
            }
        }
    }
}