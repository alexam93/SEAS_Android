package com.example.cinesaragon.ui.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cinesaragon.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()
    }

    private fun createFragment(){

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun createMarker(){
        val coordinates = LatLng(41.67083705382977, -0.8787621642615513)
        val marker = MarkerOptions().position(coordinates).title("Cine más cercano")
        map.addMarker(marker)

    }
}