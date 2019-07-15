package com.example.pregnancy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.pregnancy.dataModels.ScanBookings
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_scan_labs_booking.*
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ScanLabsBooking : AppCompatActivity() {

    private lateinit var database:DatabaseReference

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_labs_booking)


        val scanName = intent.getStringExtra("ScanName")


        val timeSlot = findViewById<EditText>(R.id.time)
        val dateSlot = findViewById<EditText>(R.id.date)

        val bookNow=findViewById<Button>(R.id.BookNowBtn)


        val labs= arrayOf("Lab 1", "Lab 2")

        val spinner=findViewById<Spinner>(R.id.spinner)


        if(spinner!=null)
        {

            val arrayAdapter=ArrayAdapter(this,R.layout.dropdown_list_textview,labs)
            spinner.adapter=arrayAdapter

            spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@ScanLabsBooking,"Please Select a Lab",Toast.LENGTH_SHORT).show()
                }

                override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    Toast.makeText(this@ScanLabsBooking,"Lab Selected",Toast.LENGTH_SHORT).show()
                }

            }
        }

        val selectedLab=spinner.selectedItem.toString()

        Log.d("SelectedLab","--------------------------------------------------------$selectedLab")


        database=FirebaseDatabase.getInstance().reference

        bookNow.setOnClickListener {

            val scanTime = timeSlot.text.toString()
            val scanDate = dateSlot.text.toString()

            Log.d("Scan Time\n\n","------------------------------------------------------------\n$scanTime")
            Log.d("Scan Time\n\n","------------------------------------------------------------\n$scanTime")


            writeInDatabase(selectedLab, "Digen", scanName, scanTime, scanDate)
            Toast.makeText(this@ScanLabsBooking,"Test Booked",Toast.LENGTH_SHORT).show()

        }









    }

    private fun writeInDatabase(lab:String,name:String, scanName:String?, timeSlot:String, date:String ) {

        val scanDetails=ScanBookings(scanName,timeSlot,date)

        database.child("Scan Bookings").child(lab).child("Bookings").child(name).setValue(scanDetails)


    }


}