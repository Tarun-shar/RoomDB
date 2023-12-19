package com.example.roomdb

import androidx.room.TypeConverter
import java.util.Date

//if we want to store any value except these datatype(NULL, INTEGER, REAL, TEXT, BLOB) so, we use convertors
class Convertors {

//    convert date object into Long
    @TypeConverter
    fun fromDateToLong( value: Date):Long{
        return value.time
    }

//    convert Long to Date
    @TypeConverter
    fun fromLongToDate( value:Long):Date{
        return Date(value)
    }
}

