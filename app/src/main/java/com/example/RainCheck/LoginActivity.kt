/**
 *
 */
package com.example.RainCheck

/**
 *
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
     * Button and text edits binding.
     */
    private lateinit var createAccountButton : Button
    private lateinit var forgotPasswordButton : Button
    private lateinit var loginButton: Button

    private lateinit var userName: EditText
    private lateinit var password: EditText

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

        /**
         * Binding for the editTexts.
         */

        /**
         * Binding for the buttons text's.
         */
        this.createAccountButton = this.binding.buttonCreateAccount
        this.forgotPasswordButton = this.binding.buttonForgot

        this.loginButton = this.binding.loginButton
    }
}