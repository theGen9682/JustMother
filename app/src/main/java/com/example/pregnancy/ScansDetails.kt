package com.example.pregnancy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_scans_details.view.*
import org.w3c.dom.Text

class ScansDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scans_details)

        val scanTitle =findViewById<TextView>(R.id.scan_Title)
        val scanInfoTitle=findViewById<TextView>(R.id.scan_Info)
        val scanContent=findViewById<TextView>(R.id.scan_Content)
        val scanNeed=findViewById<TextView>(R.id.scan_Need)
        val scanSecondDetail= findViewById<TextView>(R.id.scan_Second_Content)


        val title:String?=intent.getStringExtra("ScanName")

        Log.d("TItle\n\n","-------------------------$title")

        val db=FirebaseFirestore.getInstance()
        val details=db.collection("Scan Details").document(title.toString())


        val bookBtn=findViewById<Button>(R.id.bookBtn)


        bookBtn.setOnClickListener {
            val scanIntent= Intent(this,ScanLabsBooking::class.java)
            scanIntent.putExtra("ScanName",title)
            startActivity(scanIntent)
        }

        details.get().addOnSuccessListener {
            document->
            if(document!=null)
            {

                Log.d("Document Data\n\n","------------------------------------------\n${document.data}")

                scanTitle.text=title


                val scanInfo=document.getString("scanInfo")


                Log.d("Scan Info\n\n","-------------------------------------------------\n$scanInfo")


                val scanWhen=document.getString("scanWhen")

                Log.d("Scan When\n\n","-------------------------------------------------\n$scanWhen")

                scanContent.text=scanInfo
                scanSecondDetail.text=scanWhen
                scanInfoTitle.text=getString(R.string.scan_info)
                scanNeed.text=getString(R.string.scan_need)
            }
        }
            .addOnFailureListener {
                Log.d("Failure\n\n","DataBAse Error, Fix it!!!")
            }

    }
}
