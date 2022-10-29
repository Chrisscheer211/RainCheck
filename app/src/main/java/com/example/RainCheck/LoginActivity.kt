/**
 *
 */
package com.example.RainCheck

/**
 *
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.RainCheck.databinding.ActivityLoginBinding

/**
 *
 */
class LoginActivity : AppCompatActivity() {

    /**
     *
     */
    private lateinit var binding : ActivityLoginBinding

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         * Inflate the layout.
         */
        super.onCreate(savedInstanceState)
        this.binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}