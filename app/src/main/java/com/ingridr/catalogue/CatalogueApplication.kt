package com.ingridr.catalogue

import android.app.Application
import androidx.room.Room
import com.ingridr.catalogue.network.AppDatabase


class CatalogueApplication : Application() {

//    lateinit var dataBase : AppDatabase
    override fun onCreate() {
        super.onCreate()
//        dataBase = AppDatabase.getAppDatabase(this.applicationContext)!!
        // Required initialization logic here!
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    override fun onLowMemory() {
        super.onLowMemory()
    }
}