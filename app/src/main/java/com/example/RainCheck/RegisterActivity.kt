/**
 *
 */
package com.example.RainCheck

/**
 *
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.RainCheck.databinding.ActivityRegisterBinding

/**
 *
 */
class RegisterActivity : AppCompatActivity() {

    /**
     *
     */
    private lateinit var binding : ActivityRegisterBinding

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         * Inflate the layout.
         */
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)





    }
}