package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        accessing the database
        database = ContactDatabase.getDatabase(this)

//        make a coroutine
        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"John","99999", Date(), 1))
        }
    }

    fun getData(view: View) {
        database.contactDao().getContact().observe(this, Observer {
            Log.d("RoomDb",it.toString())
        })
    }
}

//uninstall the app from device then reinstall