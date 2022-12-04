/**
 *
 */
package com.example.raincheck

/**
 *
 */
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.raincheck.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 *
 */
class RegisterActivity : AppCompatActivity() {

    /**
     * Firebase attributes.
     */
    private lateinit var firebaseAuth: FirebaseAuth

    /**
     *
     */
    private lateinit var binding : ActivityRegisterBinding
    private val TAG: String = "Register Activity"

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

        /**
         * Set up firebase.
         */
        //this.firebaseAuth = FirebaseAuth.getInstance()
        this.firebaseAuth = Firebase.auth

        /**
         * Return to the login page.
         */
        this.binding.returnButton.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
        }

        /**
         * Create the rainCheck account with the entered credentials.
         */
        this.binding.createAccountButton.setOnClickListener {

            this.createAccount()
        }
    }

    /**
     * Create new account with user entered credentials.
     */
    fun createAccount(): Boolean{

        /**
         *
         */
        val userName = this.binding.userNameEditText.text.toString()
        val password = this.binding.passwordEditText.text.toString()
        var err = true

        /**
         *
         */
        if(userName.isEmpty() || password.isEmpty()){

            Toast.makeText(this, "Empty fields are not allowed.",
                Toast.LENGTH_SHORT).show()

            return false
        }

        /**
         *
         */
        if(password != this.binding.conformPasswordEditText.text.toString()){

            Toast.makeText(this, "Passwords to not match.",
                Toast.LENGTH_SHORT).show()

            return false
        }

        /**
         * Cheek if the user is not logged into the database.
         */
        val currentUser = this.firebaseAuth.currentUser
        if(currentUser != null) Log.e(this.TAG, "Nope")

        /**
         * Creates the new user in the firebase database.
         */
        this.firebaseAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener(this){ task ->

            if (task.isSuccessful) {

                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                startActivity(Intent(this, SplashScreen::class.java))

            } else {

                Toast.makeText(this, "Failed to create new account" + task.exception.toString(),
                    Toast.LENGTH_SHORT).show()
                err = false
            }
        }

        return err
    }
}