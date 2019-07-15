package com.example.pregnancy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import java.security.AccessControlContext

class scans : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scans)

        val scanList =findViewById<ListView>(R.id.scan_list)



        scanList.adapter=ScansListAdapter(this) //let the list view know what to render

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true) // for up button

    }

    private class ScansListAdapter(context: Context) : BaseAdapter() {

        private val scansListContext :Context= context


        private val scanNameList= arrayListOf(	"USG LEVEL1",
            "DATING SCAN",
            "NT SCAN",
            "USG LEVEL2",
            "FETOSCOPY",
            "GROWTH SCAN"
        )



        @SuppressLint("ViewHolder")
        override fun getView(position: Int, p1: View?, viewGroup: ViewGroup?): View {

            val inflateScanList = LayoutInflater.from(scansListContext)

            val scanList = inflateScanList.inflate(R.layout.scan_list_adapter, viewGroup, false )

            val scanName = scanList.findViewById<TextView>(R.id.scan_name)
            scanName.text = scanNameList[position]
            scanName.setOnClickListener{
                val scanDetailsIntent= Intent(scansListContext,ScansDetails::class.java)
                scanDetailsIntent.putExtra("ScanName", scanNameList[position])
                scanDetailsIntent.putExtra("position",position)

                scansListContext.startActivity(scanDetailsIntent)
            }

            val getScansBtn= scanList.findViewById<ImageButton>(R.id.get_scans)
            getScansBtn.setOnClickListener{
                //TODO //add fragments to add scans to cart
            }

            return scanList
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return scanNameList.size
        }

    }
}
