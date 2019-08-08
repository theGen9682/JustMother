package com.example.pregnancy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.pregnancy.dataModels.TestBookings
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_test_labs_booking.*
import java.text.SimpleDateFormat
import java.util.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TestLabsBooking : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_labs_booking)

        val testName=intent.getStringExtra("title")

        val bookingName= findViewById<EditText>(R.id.editText_Name)

        val timeSlot=findViewById<EditText>(R.id.TestTime)
        val dateSlot=findViewById<CalendarView>(R.id.dateSlot)



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

            val date =getDate(dateSlot.date)

            val name= bookingName.text.toString()
            Log.d("\n\nDate","----------------------------------------------------------------------$date")

            Log.d("\n\nName","-------------------------------------------------------------$name")


            writeInDatabase(selectedLab,name,date,time,testName)
    }

    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(milliSeconds: Long): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("dd/MM/yyyy")

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }





fun writeInDatabase(selectedLab:String,name:String,date:String,time:String,testName:String){

    val testDetails= TestBookings(testName,time,date)


    database.child("Test Boookings").child(selectedLab).child("Bookings").child(name).setValue(testDetails)


}
}

