package com.example.pregnancy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import java.security.AccessController.getContext

class tests : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)



        val testList =findViewById<ListView>(R.id.test_list)

        testList.adapter=TestsListAdapter(this)//lets the list view know what to insert


        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true) // for up button
    }

    private class TestsListAdapter(context: Context) :BaseAdapter(){

        private val testsListContext: Context = context
        private val tests= arrayListOf(
            "CBC" ,
            "TSH" ,
            "RBS" ,
            "HIV" ,
            "URINE R/M" ,
            "RPR" ,
            "GTT" ,
            "HEPB" ,
            "HCV" ,
            "HB Electrophoresis" ,
            "VDRL" ,
            "CVS" ,
            "DNA cell free test" ,
            "Amniocentesis" ,
            "NIPT"
        )


        // can ignore for now
        override fun getItem(position: Int): Any {
            return "Test String"
        }

        //can ignore for now
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        //sets the no. to row in the list view
        override fun getCount(): Int {
           return tests.size
        }


        //used to populate the list view by inflater class and reuses the the UI elements
        @SuppressLint("ViewHolder")
        override fun getView(position: Int, p1: View?, viewGroup: ViewGroup?): View {
            val inflateTestList= LayoutInflater.from(testsListContext)

           val testList = inflateTestList.inflate(R.layout.tests_list_adapter, viewGroup,false)


            val testName = testList.findViewById<TextView>(R.id.scan_name)
            testName.text= tests[position]


            testName.setOnClickListener{
               Toast.makeText(testsListContext,"Test title Clicked $position",Toast.LENGTH_SHORT).show()
                val testDetailsIntent= Intent(testsListContext,TestDetails::class.java)
                testDetailsIntent.putExtra("title",tests[position])
                testDetailsIntent.putExtra("position",position)
                testsListContext.startActivity(testDetailsIntent)

            }



            val getBtn = testList.findViewById<ImageButton>(R.id.get_tests)

            getBtn.setOnClickListener{
               Toast.makeText(testsListContext,"Get Button Clicked $position", Toast.LENGTH_SHORT).show()
            }

            return testList
        }

    }
}
