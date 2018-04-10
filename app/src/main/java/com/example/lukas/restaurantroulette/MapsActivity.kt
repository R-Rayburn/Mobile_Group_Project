package com.example.lukas.restaurantroulette

import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback{


    private lateinit var mMap: GoogleMap

    private var locationManager: LocationManager? = null

    var mLocation: Location? = null

    var lat = 0.0
    var long = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        lat = intent.getDoubleExtra("latitude", 0.0)
        long = intent.getDoubleExtra("longitude", 0.0)

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap


        //val place = LatLng(30.0,30.0)
        val place = LatLng(intent.getDoubleExtra("latitude", -30.0), intent.getDoubleExtra("longitude", -86.0))
        mMap.addMarker(MarkerOptions().position(place).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        mMap.uiSettings.isZoomControlsEnabled = true

    }

}