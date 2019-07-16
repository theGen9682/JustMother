package com.example.pregnancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.pregnancy.dataModels.ScanBookings
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_scan_labs_booking.*
import kotlinx.android.synthetic.main.activity_test_labs_booking.*

class TestLabsBooking : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_labs_booking)

        val testName=intent.getStringExtra("title")

        val bookingName= findViewById<EditText>(R.id.editText_Name_Test)

        val timeSlot=findViewById<EditText>(R.id.TestTime)
        val dateSlot=findViewById<EditText>(R.id.TestDate)


        val labs= arrayOf("Choose A Lab","Lab 1","Lab 2")

        val spinner=findViewById<Spinner>(R.id.TestSpinner)

        if(spinner!=null)
        {

            val arrayAdapter= ArrayAdapter(this,R.layout.dropdown_list_textview,labs)
            spinner.adapter=arrayAdapter

            spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@TestLabsBooking,"Please Select a Lab", Toast.LENGTH_SHORT).show()
                }

                override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    Toast.makeText(this@TestLabsBooking,"${labs[position]} Selected", Toast.LENGTH_SHORT).show()
                }

            }
        }

        database=FirebaseDatabase.getInstance().reference

        val  bookTest=findViewById<Button>(R.id.TestBookNowBtn)

        bookTest.setOnClickListener {
            val selectedLab=spinner.selectedItem.toString()
            val time=timeSlot.text.toString()
            val date=dateSlot.text.toString()
            val name=bookingName.text.toString()

            Log.d("Name","-------------------------------------------------------------$name")


            writeInDatabase(selectedLab,name,date,time,testName)
    }
}


fun writeInDatabase(selectedLab:String,name:String,date:String,time:String,testName:String){

    val testDetails= ScanBookings(testName,time,date)


    database.child("Test Boookings").child(selectedLab).child("Bookings").child(name).setValue(testDetails)


}
}
