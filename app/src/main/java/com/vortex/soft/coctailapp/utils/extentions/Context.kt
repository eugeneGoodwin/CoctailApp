package com.vortex.soft.coctailapp.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Activity>Context.startActivity(){
    val intent = Intent(applicationContext, T::class.java)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    applicationContext.startActivity(intent)
}