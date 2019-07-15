package com.example.pregnancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ScanLabsBooking : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()


    var selectedLab: String = "Lab 1"


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.lab1Button ->
                    if (checked) {
                        // Pirates are the best

                        selectedLab = "Lab 1"


                    }
                R.id.lab2Button ->
                    if (checked) {

                        selectedLab = "Lab 2"


                    }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_labs_booking)

        val scanName = intent.getStringExtra("ScanName")


        val timeSlot = findViewById<TextView>(R.id.time)
        val date = findViewById<TextView>(R.id.date)

        val scanTime = timeSlot.text.toString()
        val scanDate = date.text.toString()


        Log.d("Selected Lab\n\n","\n\n------------------------------------------------------------------------------------------$selectedLab")

        addData(scanDate,scanTime,scanName)



    }


    private fun addData(scanDate:String, scanTime:String, scanName:String)
    {
        db.collection("Scan Bookings")
            .document(selectedLab)
            .collection("Scan Dates")
            .add(scanDate)

        db.collection("Scan Bookings")
            .document(selectedLab)
            .collection("Scan Dates")
            .document(scanDate.toString())
            .collection("Time Slot")
            .document(scanTime.toString())
            .collection("Booking Details")
            .add("Digen Sharma")


        db.collection("Scan Bookings")
            .document(selectedLab)
            .collection("Scan Dates")
            .document(scanDate.toString())
            .collection("Time Slot")
            .document(scanTime.toString())
            .collection("Booking Details")
            .document("Digen Sharma")
            .set(scanName)
            .addOnSuccessListener {
                Log.d("Successfull\n\n","------------------------------------------------------------------------------------")
            }
            .addOnFailureListener {
                Log.d("Failure\n\n","------------------------------------------------------------------------------------------")
            }

    }


}