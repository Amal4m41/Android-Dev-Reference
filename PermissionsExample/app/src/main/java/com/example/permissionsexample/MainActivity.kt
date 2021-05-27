package com.example.permissionsexample

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object{
        private const val CAMERA_PERMISSION_CODE=1
        private const val FINE_LOCATION_PERMISSION_CODE=2
        private const val CAMERA_AND_FINE_LOCATION_PERMISSION_CODE=12
        //private const val SOMEOTHER_PERMISSION_CODE=100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn=findViewById<Button>(R.id.btnRequestPermission)

        btn.setOnClickListener {
            //checking if we already have the permission
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
            }
            else
            {
              //request permission
                  //we can request for multiple permissions at once, i.e pass an array of permission string values
               ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA
                   ,Manifest.permission.ACCESS_FINE_LOCATION),
                   CAMERA_AND_FINE_LOCATION_PERMISSION_CODE)
            }
        }

    }

    //this function is triggered once the permission dialog box is filled by the user
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_AND_FINE_LOCATION_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted for camera", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Oops you just denied the permission for camera. You can also allow it from settings",
                    Toast.LENGTH_SHORT).show()
            }

            if(grantResults.isNotEmpty() && grantResults[1] ==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted for location/gps", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Permission denied for location/gps", Toast.LENGTH_SHORT).show()
            }
        }
    }


}