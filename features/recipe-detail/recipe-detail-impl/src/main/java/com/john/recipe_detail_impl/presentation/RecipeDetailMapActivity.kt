package com.john.recipe_detail_impl.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.john.recipe_detail_impl.databinding.DetailMapActivityBinding
import com.john.recipe_detail_impl.domain.entities.Coordinates

class RecipeDetailMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var _binding: DetailMapActivityBinding? = null
    private val binding: DetailMapActivityBinding get() = _binding!!

    private lateinit var googleMap: GoogleMap
    private var coordinates: Coordinates? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DetailMapActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtrasFromIntent()
        checkPermissions()
    }

    private fun getExtrasFromIntent() {
        coordinates = intent?.extras?.getParcelable(COORDINATES_KEY)
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            setupMap()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_LOCATION
            )
        }
    }

    private fun setupMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(binding.googleMapFragment.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        setupMarket()
    }

    private fun setupMarket() {
        coordinates?.let {
            val markerPosition = LatLng(it.latitude, it.longitude)
            val markerTitle = "Marker Title"
            googleMap.addMarker(MarkerOptions().position(markerPosition).title(markerTitle))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, DEFAULT_ZOOM))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val DEFAULT_ZOOM = 15F
        private const val PERMISSIONS_REQUEST_LOCATION = 123
        const val COORDINATES_KEY = "COORDINATES_KEY"
    }
}