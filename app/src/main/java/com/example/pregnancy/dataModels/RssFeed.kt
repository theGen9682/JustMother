package com.example.pregnancy.dataModels

import java.util.*

data  class RssFeed (val status:String, val feed:Feed, val items:ArrayList<Items>)

data class Feed (val url:String, val title:String, val link:String,val author:String, val description:String, val image:String)

data class Items(val title:String, val pubDate: String, val link:String, val guid:String, val author:String, val thumbnail:String, val description:String,
                 val content:String, val enclosure:Enclosure, val categories:List<String> )

data class Enclosure(val enclosureList:String)

