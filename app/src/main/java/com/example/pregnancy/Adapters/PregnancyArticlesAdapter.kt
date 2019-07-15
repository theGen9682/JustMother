package com.example.pregnancy.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancy.R

import com.example.pregnancy.dataModels.RssFeed
import kotlinx.android.synthetic.main.preg_articles_adapter.view.*

class PregnancyArticlesAdapter(private val rssfeed: RssFeed, val context:Context) :RecyclerView.Adapter<CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)

        val cell=layoutInflater.inflate(R.layout.preg_articles_adapter,parent,false)

        return CustomViewHolder(cell)

    }

    override fun getItemCount(): Int {
        return rssfeed.items.size   }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.feedTitle_textView.text=rssfeed.items[position].title
        holder.view.pubDate_textView.text="Published On: ${rssfeed.items[position].pubDate}"

       for(i in 1..rssfeed.items.size) {
           val desc = rssfeed.items[position].description.split("<p>")

           val descfinal = desc[1].split("</p>")
           println("----------Printing Desc----------------")
           println(desc)


           println("--------------------------------------Printing DescFinal--------------------------------------------------")
           println(descfinal)

           val description=descfinal[0]

           holder.view.description_textView.text=description
       }


        holder.view.setOnClickListener {
            val browserInent=Intent(Intent.ACTION_VIEW, Uri.parse(rssfeed.items[position].link))
            context.startActivity(browserInent)
        }
    }


}



class CustomViewHolder(val view : View ):RecyclerView.ViewHolder(view)