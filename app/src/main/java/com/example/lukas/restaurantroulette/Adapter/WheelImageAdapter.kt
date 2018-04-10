package com.example.lukas.restaurantroulette.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.lukas.restaurantroulette.Data.ImageData
import com.example.lukas.restaurantroulette.Localspots
import com.example.lukas.restaurantroulette.R
import github.hellocsl.cursorwheel.CursorWheelLayout

/**
 * Created by lukas on 4/4/2018.
 */




class WheelImageAdapter(private val mContext: Context, private val menuItems: List<ImageData>) : CursorWheelLayout.CycleWheelAdapter() {
    private val inflater: LayoutInflater
    private val gravity: Int = 0

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun getCount(): Int {
        return menuItems.size
    }

    override fun getView(parent: View, position: Int): View {
        val data = getItem(position)
        val root = inflater.inflate(R.layout.image_wheel_layout, null, false)
        val imageView: ImageView = root.findViewById<View>(R.id.wheel_menu_item_iv) as ImageView
        imageView.setImageResource(data.imageResource)
        return root
    }

    override fun getItem(position: Int): ImageData {
        return menuItems[position]
    }

}