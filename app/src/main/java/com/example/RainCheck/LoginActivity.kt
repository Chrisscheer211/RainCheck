/**
 * The main project package.
 */
package com.example.RainCheck

/**
 * Other imported packages.
 */
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.RainCheck.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * Google sign in imported packages.
 */
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

/**
 * Object decluration for login activity.
 */
class LoginActivity : AppCompatActivity() {

    /**
     * Firebase attributes.
     */
    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    /**
     * Google account objects.
     */
    private lateinit var gso : GoogleSignInOptions
    private lateinit var gsc : GoogleSignInClient
    private val RC_SIGN_IN = 9001   //DO NOT CHANGE!
//    private var storageRef = Firebase.storage.reference

    /**
     *
     */
    private val TAG: String = "Login Activity"

    /**
     * Overloads the login activity objects on create method.
     */
    override fun onCreate(savedInstanceState: Bundle?){

        /**
         * Inflate the layout.
         */
        super.onCreate(savedInstanceState)
        this.binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /**
         * Set up firebase.
         */
        this.firebaseAuth = FirebaseAuth.getInstance()

        /**
         * Log's in to rainCheck with the rainCheck credentials.
         */
        this.binding.loginButton.setOnClickListener {

            this.login()
        }

        /**
         * Enable account creation and changes to the create account activity.
         */
        this.binding.rainCheckCreateAccountButton.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }

        /**
         * Enable the google sign when button is clicked.
         */
        this.binding.googleSigninButton.setOnClickListener {

            /**
             * Setting up google login...
             */
            this.gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

            //TODO Fix decricated code later
            this.gsc = GoogleSignIn.getClient(this, this.gso)
            startActivityForResult(gsc.signInIntent, RC_SIGN_IN)
        }
    }

    /**
     * Log in with credentials for rain check.
     */
    private fun login() : Boolean{

        /**
         * Variable declaration.
         */
        val email = this.binding.userNameEditText.text.toString()
        val password = this.binding.passwordEditText.text.toString()
        var err: Boolean = false

        /**
         * Validation for empty inputs.
         */
        if(email.isEmpty() || password.isEmpty()){

            Toast.makeText(this, "Empty Fields Are not Allowed !!",
                Toast.LENGTH_SHORT).show()

            return false
        }

        /**
         * Validate of the credentials entered are on record.
         */
        this.firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if(it.isSuccessful){

                startActivity(Intent(this, RegisterActivity::class.java))
            }
            else{

                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        return false
    }

    /**
     * TODO add commend here...
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    /**
     * TODO add commend here...
     */
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        try {

            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        }

        /**
         * If a exception is thrown then...
         */
        catch (e: ApiException) {

            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    /**
     * The action to take when the user signs in with there google account.
     */
    private fun updateUI(account: GoogleSignInAccount?) {

        startActivity(Intent(this, SplashScreen::class.java))
    }
}