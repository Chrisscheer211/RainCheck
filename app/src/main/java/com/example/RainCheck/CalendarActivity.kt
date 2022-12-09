/**
 * The main project package.
 */
package com.example.raincheck


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import com.example.raincheck.databinding.ActivityCalanderBinding
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {

    /*** Private Attributes.*/
    private lateinit var binding: ActivityCalanderBinding
    private lateinit var calendar: CalendarHelper

    private lateinit var dateString: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.binding = ActivityCalanderBinding.inflate(layoutInflater)

        setContentView(this.binding.root)

        /*** Sets a text view with the date the users selects on the calendar.*/
        this.binding.calanderCalanderView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->

                /*** Converts the date to the string that is used later.*/
                this.dateString =
                    (year.toString() + "-" + (month + 1) + "-" + dayOfMonth.toString())

                this.binding.dateTextTextView.text = dateString
            }
        )

        this.binding.button.setOnClickListener {

            if (binding.dateTextTextView.text == "Select a date.") {
                Toast.makeText(
                    this, "Select a date!!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                this.calendar = CalendarHelper()
                this.calendar.setDateWithString(
                    this.dateString + "T09:00:00",
                    this.dateString + "T12:00:00"
                )
                startActivity(this.calendar.createCalendarEvent("RainCheck Appointment"))
            }
        }

        this.binding.backButton.setOnClickListener {

            var intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }
}