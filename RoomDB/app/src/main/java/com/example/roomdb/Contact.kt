package com.example.roomdb

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val phone: String,
    val createdDate: Date, // we can store values directly in room but we can not store date directly in room

    val isActive: Int // it store 0 or 1 and this column must be add in the database of existing users
)
