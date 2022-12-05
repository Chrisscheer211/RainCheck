/**
 *
 */
package com.example.RainCheck

/**
 *
 */
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RainCheck.databinding.ActivityHelpBinding

/**
 *
 */
class HelpActivity : AppCompatActivity() {

    /**
     * Attributes.
     */
    private lateinit var binding: ActivityHelpBinding

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         *
         */
        super.onCreate(savedInstanceState)
        this.binding = ActivityHelpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /**
         * Sets up the recyclerview.
         */
        this.binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<Question>()

        val answers = resources.getStringArray(R.array.Answers)
        val questions = resources.getStringArray(R.array.Questions)

        /**
         * Sets the questions and answers.
         */
        for (i in 0..4){

            data.add(Question(questions?.get(i).toString(), answers?.get(i).toString()))
        }

        this.binding.recyclerview.adapter = QuestionAdapter(data)

        /**
         * When clicked, invokes the call support method.
         */
        this.binding.supportNumberTextView.setOnClickListener {

            this.callSupport()
        }

        /**
         * When clicked, invokes the call support method.
         */
        this.binding.supportNumberImageView.setOnClickListener {

            this.callSupport()
        }

        /**
         * When clicked, invokes the send email to support method.
         */
        this.binding.supportEmailImageView.setOnClickListener {

            this.sendEmail()
        }

        /**
         * When clicked, invokes the send email to support method.
         */
        this.binding.supportEmailTextView.setOnClickListener {

            this.sendEmail()
        }

        /**
         * Gose back to the login screen.
         */
        this.binding.backButton.setOnClickListener{

            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    /**
     * Call the support number when clicked.
     */
    private fun callSupport(): Boolean{

        startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "6315441178")))
        return true
    }

    /**
     * Creates a email for the user to fill out and sent.
     */
    @SuppressLint("IntentReset")
    private fun sendEmail(): Boolean{

        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        /**
         * Sets up the email stuff.
         */
        mIntent.putExtra(Intent.EXTRA_EMAIL, "rjgilbride60@gmail.com")
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "Help!!!")
        mIntent.putExtra(Intent.EXTRA_TEXT, "")

        try {

            startActivity(Intent.createChooser(mIntent, "Gmail"))
        }
        catch (e: Exception){

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
        return true
    }
}