/**
 * The main project package.
 */
package com.example.raincheck


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.raincheck.databinding.ActivityCalanderBinding

class CalanderActivity : AppCompatActivity() {

    /*** Private Attributes.*/
    private lateinit var binding: ActivityCalanderBinding
    private lateinit var calander: CalendarHelper

    private lateinit var dateString: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.binding = ActivityCalanderBinding.inflate(layoutInflater)

        setContentView(this.binding.root)

        /*** Sets a text view with the date the users selects on the calendar.*/
        this.binding.calanderCalanderView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->

                /*** Converts the date to the string that is used later.*/
                this.dateString = (year.toString() + "-" + (month + 1) + "-" + dayOfMonth.toString())
                this.binding.dateTextTextView.setText(dateString)
            }
        )

        this.binding.button.setOnClickListener {

            this.calander = CalendarHelper()
            this.calander.setDateWithString(this.dateString + "T09:00:00", this.dateString + "T12:00:00")
            //this.calander.setDateWithString("2022-02-1T09:00:00", "2022-02-1T12:00:00")

            startActivity(this.calander.createCalendarEvent("RainCheck Appointment"))
        }

        this.binding.backButton.setOnClickListener {

            var intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }
}