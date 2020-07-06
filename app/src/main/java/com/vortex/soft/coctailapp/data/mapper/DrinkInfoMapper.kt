package com.vortex.soft.coctailapp.data.mapper

import com.vortex.soft.coctailapp.data.model.DrinkInfo
import com.vortex.soft.coctailapp.domain.model.DDrinkInfo

class DrinkInfoMapper : Mapper<DrinkInfo, DDrinkInfo> {
    override fun mapFromEntity(type: DrinkInfo) = DDrinkInfo(type.strDrink, type.strDrinkThumb, type.idDrink)
    override fun mapToEntity(type: DDrinkInfo): DrinkInfo = DrinkInfo(type.strDrink, type.strDrinkThumb, type.idDrink)
}