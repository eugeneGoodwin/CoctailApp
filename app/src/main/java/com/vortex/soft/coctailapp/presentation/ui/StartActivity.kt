package com.vortex.soft.coctailapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import com.vortex.soft.coctailapp.R
import com.vortex.soft.coctailapp.utils.extension.startActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        loadImage()
        start_motion_layout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                startActivity()
            }
        })
    }

    fun startActivity(){
        startActivity<MainActivity>()
    }

    fun loadImage(){
        //val input: InputStream? = javaClass.getResourceAsStream("/drawable/start_drink.jpg")
        //start_image_view.setImageDrawable(Drawable.createFromStream(input, ""))
        start_image_view.setImageResource(R.drawable.start_drink)
    }
}