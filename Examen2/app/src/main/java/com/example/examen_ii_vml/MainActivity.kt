package com.example.examen_ii_vml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.TextView
import com.example.examen_ii_vml.io.response.DistanceResponse
import com.example.examen_ii_vml.io.respose.ApiService
import com.example.examen_ii_vml.model.Step
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


import android.annotation.SuppressLint
import android.graphics.Color
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        private const val FROM_REQUEST_CODE = 1
        private const val TO_REQUEST_CODE = 2
        private const val TAG = "MainActivity"
    }

    private lateinit var mMap: GoogleMap

    private var mFromLatLng: LatLng? = null
    private var mToLatLng: LatLng? = null

    private var mMarkerTo: Marker? = null
    private var mMarkerFrom: Marker? = null

    private var lastPolyline: Polyline? = null

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
                            setMarkerFrom(it)
                    }
                }
                return
            }else if(requestCode == TO_REQUEST_CODE){
                processAutoCompleteResult(resultCode, data) { place ->
                    dirDestino.text = "${place.address}"
                    place.latLng?.let {
                        mToLatLng = it
                        setMarkerTo(it)
                    }
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
        mMap.setMaxZoomPreference(30f)
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
        computerTravelInfo()
    }
    private fun setMarkerTo(latLng: LatLng){
        mMarkerTo?.remove()
        mMarkerTo = addMarker(latLng, "Destino")
        computerTravelInfo()
    }
    private fun computerTravelInfo(){
        val from = mFromLatLng?.let { latLngToStr(it) }
        val to = mToLatLng?.let { latLngToStr(it) }
        if(from == null || to == null){
            return
        }
        ApiService.create().getDistance(from, to).enqueue(object: Callback<DistanceResponse> {
            override fun onResponse(call: Call<DistanceResponse>, response: Response<DistanceResponse>) {
                if(response.isSuccessful){
                    response.body()?.let { displayTravelInfo(it) }
                }else{
                    Log.d(TAG, "")
                }
            }
            override fun onFailure(call: Call<DistanceResponse>, t: Throwable){
                Log.d(TAG, t.localizedMessage ?: "")
            }

        })
    }
    private fun displayTravelInfo(distanceResponse: DistanceResponse) {
        //txtDistancia.text = "Distancia: ${distanceResponse.distance} metros"
        //tiempo.text = "Tiempo: ${distanceResponse.time} minutos"

        drawRoute(distanceResponse.steps)
    }

    private fun drawRoute(steps: List<Step>?){
        Log.d(TAG, "drawRoute(...)")
        lastPolyline?.remove()

        if(steps == null || steps.isEmpty()){
            Log.d(TAG, "No se ha podido dibujar la ruta")
            return
        }
        val options = PolylineOptions().clickable(true)
        options.add(steps[0].start_location.toLatLng())
        steps.forEach{
            options.add(it.end_location.toLatLng())
        }
        lastPolyline = mMap.addPolyline(options)
    }

    private fun latLngToStr(latLng: LatLng) = "${latLng.latitude}, ${latLng.longitude}"


}