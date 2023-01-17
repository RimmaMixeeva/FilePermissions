package com.example.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissions.databinding.ActivityMainBinding
const val CAMERA_CODE = 1
const val AUDIO_CODE = 2
const val LOCATION_CODE = 3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestCamera.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_CODE
                )
            else   {
                onPermissionGranted(CAMERA_CODE)}
        }

        binding.requestAudio.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    AUDIO_CODE
                )
            else   {
                onPermissionGranted(AUDIO_CODE)}

        }
        binding.requestLocation.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    LOCATION_CODE
                )
            else   {
                onPermissionGranted(LOCATION_CODE)}

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            onPermissionGranted(requestCode)
        }
        else {
            onPermissionDenied(requestCode)
        }
    }

    private fun onPermissionGranted(key: Int) {
        when (key) {
            CAMERA_CODE->{
                Toast.makeText(this,"Camera permission is granted", Toast.LENGTH_SHORT).show()
                }
            AUDIO_CODE-> {
                Toast.makeText(this,"Audio permission is granted", Toast.LENGTH_SHORT).show()
            }
            LOCATION_CODE-> {
                Toast.makeText(this,"Location permission is granted", Toast.LENGTH_SHORT).show()
            }
            }
    }
    private fun onPermissionDenied(key: Int) {
        when (key) {
            CAMERA_CODE->{
                Toast.makeText(this,"Camera permission is Denied", Toast.LENGTH_SHORT).show()
            }
            AUDIO_CODE-> {
                Toast.makeText(this,"Audio permission is Denied", Toast.LENGTH_SHORT).show()
            }
            LOCATION_CODE-> {
                Toast.makeText(this,"Location permission is Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}