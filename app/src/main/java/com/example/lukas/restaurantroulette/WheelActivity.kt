
/**
 * Created by lukas on 4/4/2018.
 */

package com.example.lukas.restaurantroulette

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.lukas.restaurantroulette.Adapter.WheelImageAdapter
import com.example.lukas.restaurantroulette.Data.ImageData
import github.hellocsl.cursorwheel.CursorWheelLayout
import kotlinx.android.synthetic.main.activity_wheel.*


class WheelActivity : AppCompatActivity(), CursorWheelLayout.OnMenuSelectedListener {

    var wheel_image: CursorWheelLayout? = null
    var lstImage: List<ImageData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel)

        initViews()

        loadData()

        wheel_image?.setOnMenuSelectedListener(this)




    }

    private fun loadData() {
        lstImage = ArrayList<ImageData>()
        (lstImage as ArrayList<ImageData>).add(ImageData(R.drawable.actuallydrink, "Actually Drink"))
        (lstImage as ArrayList<ImageData>).add(ImageData(R.drawable.bluechef, "The Blue Chef"))
        (lstImage as ArrayList<ImageData>).add(ImageData(R.drawable.chef, "The Not Blue Chef"))
        (lstImage as ArrayList<ImageData>).add(ImageData(R.drawable.drink, "Another Drink"))
        (lstImage as ArrayList<ImageData>).add(ImageData(R.drawable.actuallydrink, "Actually Drink"))
        (lstImage as ArrayList<ImageData>).add(ImageData(R.drawable.chef, "The Not Blue Chef"))


        val imgAdapter = WheelImageAdapter(getBaseContext(), lstImage as ArrayList<ImageData>)
        wheel_image?.setAdapter(imgAdapter)
    }

    private fun initViews(){
        wheel_image = findViewById(R.id.image_wheel);
    }

    override fun onItemSelected(parent: CursorWheelLayout?, view: View?, pos: Int) {
        if(parent?.getId() == R.id.image_wheel){
            //Toast.makeText(getBaseContext(), "Selected: " + lstImage?.get(pos)?.imageDescription,Toast.LENGTH_SHORT).show()
            //var restaurantPicked = lstImage?.get(pos)?.imageDescription
            var restaurantPicked = this.intent.getStringArrayExtra("name")
            textView.setText("You picked "+ restaurantPicked)
        }
    }
}