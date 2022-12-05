package com.example.raincheck

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.raincheck.*
import com.google.android.material.navigation.NavigationView
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var location: EditText
    lateinit var update: Button
    lateinit var cancel: Button
    var city: String = "New York"
    val key: String = "af2b0e768df989c01f51e780365ff8d3"
    lateinit var iconView: ImageView
    lateinit var clock: TextClock
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Weather Implementation*/

        location = findViewById(R.id.editTxt1)
        update = findViewById(R.id.updateBtn)
        cancel = findViewById(R.id.cancelBtn)
        iconView = findViewById(R.id.weather_icon)
        clock = findViewById(R.id.clock)

        location.setOnClickListener {
            findViewById<Button>(R.id.updateBtn).visibility = View.VISIBLE
            findViewById<Button>(R.id.cancelBtn).visibility = View.VISIBLE

            update.setOnClickListener {
                city = location.getText().toString()
                findViewById<Button>(R.id.updateBtn).visibility = View.GONE
                findViewById<Button>(R.id.cancelBtn).visibility = View.GONE
                location.setCursorVisible(false)
                weatherTask().execute()
            }
            cancel.setOnClickListener {
                location.setText(city)
                findViewById<Button>(R.id.updateBtn).visibility = View.GONE
                findViewById<Button>(R.id.cancelBtn).visibility = View.GONE
                location.setCursorVisible(false)
            }
        }

        weatherTask().execute()

        /** Navigation Drawer*/

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /** Navigation Drawer Change view to activity or fragment*/
        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            var intentMain = Intent(this, MainActivity::class.java)
            var intentCalendar = Intent(this, CalanderActivity::class.java)
            var intentHelp = Intent(this, HelpActivity::class.java)

            when (it.itemId) {
                R.id.nav_home -> startActivity(intentMain)
                R.id.nav_appointment -> replaceFragment(AppointmentFragment(), it.title.toString())
                R.id.nav_calendar -> startActivity(intentCalendar)
                R.id.nav_contact -> replaceFragment(ContactFragment(), it.title.toString())
                R.id.nav_notification -> replaceFragment(
                    NotificationFragment(),
                    it.title.toString()
                )
                R.id.nav_settings -> replaceFragment(SettingsFragment(), it.title.toString())
                R.id.nav_help -> startActivity(intentHelp)
            }
            true
        }
    }

    /** Function calls fragment manager*/

    private fun replaceFragment(fragment: Fragment, title: String) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    /** Sets view to item selected*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /** Open Weather Api*/
    inner class weatherTask() : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=imperial&appid=af2b0e768df989c01f51e780365ff8d3")
                        .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {

            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val temp = main.getString("temp") + "°F"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°F"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°F"
                val feel = "Feels like: " + main.getString("feels_like") + "°"
                val icon = weather.getString("icon")
                val weatherDescription = weather.getString("description")


                findViewById<TextView>(R.id.weather_desc).text = weatherDescription
                findViewById<TextView>(R.id.main_temp).text = temp
                findViewById<TextView>(R.id.min_temp).text = tempMin
                findViewById<TextView>(R.id.max_temp).text = tempMax
                findViewById<TextView>(R.id.feels_like).text = feel

                /** Sets icon based on temperature and time */

                when (icon.toString()) {
                    "01d" -> iconView.setImageResource(R.drawable.clearsky)
                    "01n" -> iconView.setImageResource(R.drawable.clearskysnight)
                    "02d" -> iconView.setImageResource(R.drawable.fewclouds)
                    "02n" -> iconView.setImageResource(R.drawable.fewcloudsnight)
                    "03d" -> iconView.setImageResource(R.drawable.scatteredclouds)
                    "03n" -> iconView.setImageResource(R.drawable.scatteredclouds)
                    "04d" -> iconView.setImageResource(R.drawable.brokenclouds)
                    "04n" -> iconView.setImageResource(R.drawable.brokenclouds)
                    "09d" -> iconView.setImageResource(R.drawable.showerrain)
                    "09n" -> iconView.setImageResource(R.drawable.showerrain)
                    "10d" -> iconView.setImageResource(R.drawable.rain)
                    "10n" -> iconView.setImageResource(R.drawable.rainnight)
                    "11d" -> iconView.setImageResource(R.drawable.thunderstorm)
                    "11n" -> iconView.setImageResource(R.drawable.thunderstorm)
                    "13d" -> iconView.setImageResource(R.drawable.snow)
                    "13n" -> iconView.setImageResource(R.drawable.snow)
                    "50d" -> iconView.setImageResource(R.drawable.mist)
                    "50n" -> iconView.setImageResource(R.drawable.mist)
                }
            } catch (e: Exception) {
                findViewById<LinearLayout>(R.id.linear2).visibility = View.GONE
            }
        }
    }
}