package com.example.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2) // Updated version is 2
@TypeConverters(Convertors::class)
abstract class ContactDatabase:RoomDatabase() {

    abstract fun contactDao() : ContactDAO

//  Implementation of singleton pattern

//    companion object means without calling the object we can access it's methods by the help of class name
    companion object{

//    create object for migration
    val migration_1_2 = object : Migration(1,2){  //here 1 is current version and 2 is updated version
//    migrate related code are writing inside this migrate method
    override fun migrate(db: SupportSQLiteDatabase) {
//        add column
        db.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
    }
}

//    private field use for holding the database
        @Volatile
        private var INSTANCE: ContactDatabase? = null //if here is assigning any value then every threads know the updated value

//    function use for accessing the database
    fun getDatabase(context: Context): ContactDatabase{
        if(INSTANCE == null){
//            use looking for creating only one instance
            synchronized(this){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                ContactDatabase::class.java,
                "contactDB")
                    .addMigrations(migration_1_2)
                    .build()
            }
        }
        return INSTANCE!!
        }
    }
}