package com.example.examen_ii_vml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        private const val FROM_REQUEST_CODE = 1
        private const val TO_REQUEST_CODE = 2
        private const val TAG = "MainActivity"
    }

    private lateinit var mMap: GoogleMap

    private lateinit var mFromLatLng: LatLng
    private lateinit var mToLatLng: LatLng

    private var mMarkerTo: Marker? = null
    private var mMarkerFrom: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMap()
        setupPlaces()
    }

    private fun setupMap(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupPlaces(){
        Places.initialize(applicationContext, getString(R.string.apiKey))
        btnOrigen.setOnClickListener{
            starAutocomplete(FROM_REQUEST_CODE)
        }
        btnDestino.setOnClickListener{
            starAutocomplete(TO_REQUEST_CODE)
        }

        //dirDestino.text = "{getString(R.string.no_place_selected)}"
        //dirDestino.text = "{getString(R.string.no_place_selected)}"
    }
    private fun starAutocomplete(requestCode: Int){
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)

        // Start the autocomplete intent.
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(this)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FROM_REQUEST_CODE) {
                processAutoCompleteResult(resultCode, data){ place ->
                    dirOrigen.text = "${place.address}"
                    place.latLng?.let {
                            mFromLatLng = it
                    }
                    setMarkerFrom(mFromLatLng)
                }
                return
            }else if(requestCode == TO_REQUEST_CODE){
                processAutoCompleteResult(resultCode, data) { place ->
                    dirDestino.text = "${place.address}"
                    place.latLng?.let {
                        mToLatLng = it
                    }
                    setMarkerTo(mToLatLng)
                }
                return
            }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processAutoCompleteResult(resultCode: Int, data: Intent?, callback: (Place) -> Unit){
        Log.d(TAG, "processAutoCompleteResult(result=$resultCode)")
        when (resultCode) {
            Activity.RESULT_OK -> {
                data?.let {
                    val place = Autocomplete.getPlaceFromIntent(data)
                    Log.i(TAG, "Place: $place")

                    //textView.text = getString(stringg, place.address)
                    callback(place)

                }
            }
            AutocompleteActivity.RESULT_ERROR -> {
                data?.let {
                    val status = Autocomplete.getStatusFromIntent(data)
                    status.statusMessage?.let {
                            message -> Log.i(TAG, message) }
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(5f)
        mMap.setMaxZoomPreference(20f)
    }
    private fun addMarker(latLng: LatLng, title: String): Marker? {
        var markerOptions = MarkerOptions()
            .position(latLng)
            .title(title)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        return  mMap.addMarker(markerOptions)

        //return mMap.addMarker(markerOptions)

    }
    private fun setMarkerFrom(latLng: LatLng){
        mMarkerFrom?.remove()
        mMarkerFrom = addMarker(latLng, "Origen")
    }
    private fun setMarkerTo(latLng: LatLng){
        mMarkerTo?.remove()
        mMarkerTo = addMarker(latLng, "Destino")
    }
}