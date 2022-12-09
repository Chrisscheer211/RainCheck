package com.example.raincheck

import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class CalendarHelper {


    /*** Private Attributes.*/
    private lateinit var sTime: Date    //Start time.
    private lateinit var eTime: Date    //End time.

    /*** Set the date with a string representation.*/
    public fun setDateWithString(sDate: String, eDate:String): Boolean{


        if(sDate.equals(null)) return false
        if(eDate.equals(null)) return false

        /*** Test the follows block of code to NullPointerException or IllegalArgumentException.*/
        try {

            val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            this.sTime = mSimpleDateFormat.parse(sDate) as Date
            this.eTime = mSimpleDateFormat.parse(eDate) as Date
        }

        catch (e: java.lang.Exception){

            Log.e("Calander", e.toString())
            return false
        }

        return true
    }

    /*** Set calendar event.*/
    public fun createCalendarEvent(title: String): Intent {

        /****/
        val mIntent: Intent = Intent(Intent.ACTION_EDIT)

        /****/
        if(title.isNotEmpty()){

            mIntent.type = "vnd.android.cursor.item/event"
            mIntent.putExtra("beginTime", this.sTime.time)
            mIntent.putExtra("time", true)
            mIntent.putExtra("rule", "FREQ=YEARLY")
            mIntent.putExtra("endTime", this.eTime.time)
            mIntent.putExtra("title",  title)
        }

        return mIntent
    }

    /*** Sets the date to be set in the calendar.*/
    private fun createCalenderEvent(
        sYear: Short, sDay : Short, sMonth : Short, sHour: Byte, sMin: Byte,
        eYear: Short, eDay : Short, eMonth : Short, eHour: Byte, eMin: Byte
    ) : Boolean{

        return true
    }
}