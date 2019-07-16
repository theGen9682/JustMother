package com.example.pregnancy

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.example.pregnancy.dataModels.Details
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream



class TestDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_details)

        val db= FirebaseFirestore.getInstance()

        val  infoTitle= "What is this test?"
        val needTitle="Why do I need this test?"

        val testName:String?=intent.getStringExtra("title")

        val testTitle=findViewById<TextView>(R.id.test_Title)
        val testInfoTextView=findViewById<TextView>(R.id.test_Info)
        val testNeedTextView=findViewById<TextView>(R.id.test_Need)
        val testImage=findViewById<ImageView>(R.id.test_Image)
        testInfoTextView.text=infoTitle
        testNeedTextView.text=needTitle

        val content=findViewById<TextView>(R.id.test_Content)

        val secondContent=findViewById<TextView>(R.id.test_Second_Content)

        val title:String?=intent.getStringExtra("title")



        testTitle.text=title

        Log.d("Test title", "----------------------------------------------------------------------$title")

        val details=db.collection("Test Details").document(title.toString())
        details.get().addOnSuccessListener {
            document->
                if (document!=null){

                    val testInfo:String?=document.getString("testInfo")
                    val testNeed:String?=document.getString("testNeed")
                    content.text=testInfo
                    secondContent.text=testNeed

                    //Log.d("Document Data\n\n","----------------------------------------------------------------------------------------------\n DocumentSnapshot data: ${document.data}")
                    //Log.d("TestInfo\n\n","-------------------------------------------------------------------$testInfo\n\n")

                }

            else {
                    Log.d("Error\n\n","---------------------------------------------------------\nNo Such document")
                }
        }
            .addOnFailureListener {
                Log.d("Failed\n\n","---------------------------------------------------------------\nFailed")
            }

        val bookTest=findViewById<Button>(R.id.BookTestNowBtn)

        bookTest.setOnClickListener {
            val bookTestIntent= Intent(this@TestDetails,TestLabsBooking::class.java)
            bookTestIntent.putExtra("title",testName)
            startActivity(bookTestIntent)
        }






    }
}

