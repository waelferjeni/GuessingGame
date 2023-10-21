package com.example.guessingnumber

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class ExpertMode : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var  editText: EditText
    lateinit var imageButtonReset: ImageButton
    lateinit var imageButtonCheck: ImageButton
    var number: Int = 0
    var random: Int = Random.nextInt(1, 100)
    private lateinit var countdownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert_mode)
        val countdownTextView = findViewById<TextView>(R.id.countdownTextView)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        imageButtonReset = findViewById(R.id.imageButtonReset)
        imageButtonCheck = findViewById(R.id.imageButtonCheck)
        textView.text= "Please enter your guess:"
        countdownTimer = object : CountDownTimer(21000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val sc: Long=5
                if(secondsRemaining == sc){
                    countDownSound()
                }
                countdownTextView.text = "Countdown: $secondsRemaining seconds"
            }

            override fun onFinish() {
                countdownTextView.text = "Countdown: 0 seconds"
                    timeIsUpSound()
                    //Toast.makeText(this@ExpertMode, "Sorry, your Time is up. Try Again", Toast.LENGTH_SHORT).show()
                    //reset()
                    showToast()
                // Perform an action when the countdown finishes
            }
        }
        countdownTimer.start()
        imageButtonCheck.setOnClickListener{
            if (editText.text.toString()==""){
                textView.text = "Please put a guess!!"
            }
            else {
                number = editText.text.toString().toInt()
                if (number < random) {

                    textView.text = "My number is greater than  " + number
                    editText.text.clear()
                    greaterSound()

                } else if (number > random) {
                    textView.text = "My number is less than  " + number
                    editText.text.clear()
                    lessSound()

                } else {

                    textView.text = "Congratulation !!!"
                    editText.text.clear()
                    countdownTimer.cancel()
                    successSound()
                    congratsToast()
                }
                imageButtonReset.setOnClickListener{
                    reset()

                }
            }

        }

    }
    override fun onBackPressed() {
        // Your custom behavior when the back button is pressed
        // For example, you can show a confirmation dialog or navigate to a different screen.

        // To call the default behavior (go back in the navigation stack), use super.onBackPressed()

    }
    fun reset() {
        countdownTimer.start()
        random = Random.nextInt(1, 100)
        textView.text= "Please enter your guess:"
        editText.text.clear()
    }

    fun showToast() {
        val toast = Toast.makeText(applicationContext, "Sorry, Your time is up", Toast.LENGTH_SHORT)
        toast.show()
        var intentMain = Intent(this, MainPage::class.java)

        // Show a confirmation dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Time's Up ")
        builder.setMessage("Do you want to try again?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            // User confirmed
            reset()
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int ->
            // User declined
            startActivity(intentMain)
            countdownTimer.cancel()
            finish()
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun congratsToast(){
        val toast = Toast.makeText(applicationContext, "Congrats, Your found my number", Toast.LENGTH_SHORT)
        toast.show()
        var intentMain = Intent(this, MainPage::class.java)

        // Show a confirmation dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Congratulaation ")
        builder.setMessage("Do you want to play again?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            // User confirmed
            reset()
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int ->
            // User declined
            startActivity(intentMain)
            finish()
            countdownTimer.cancel()

        }
        /* builder.setOnDismissListener {
            // This will be called when the dialog is dismissed without clicking the positive or negative button.
            // You can perform your action here.
            // For example, you can dismiss the dialog without any action or show a toast message.
            // dialog.dismiss() // This line dismisses the dialog.
            startActivity(intentMain)
        }*/
        val dialog = builder.create()
        dialog.show()
    }
    fun successSound(){
        val mediaPlayer = MediaPlayer.create(this,R.raw.congrats ) // Replace with the name of your audio file
        mediaPlayer.setOnPreparedListener {
            // The media player is prepared and ready to play
            mediaPlayer.start() // Start playing the audio
        }
    }
    fun greaterSound(){
        val mediaPlayer = MediaPlayer.create(this,R.raw.greater ) // Replace with the name of your audio file
        mediaPlayer.setOnPreparedListener {
            // The media player is prepared and ready to play
            mediaPlayer.start() // Start playing the audio
        }
    }
    fun lessSound(){
        val mediaPlayer = MediaPlayer.create(this,R.raw.less ) // Replace with the name of your audio file
        mediaPlayer.setOnPreparedListener {
            // The media player is prepared and ready to play
            mediaPlayer.start() // Start playing the audio
        }
    }
    fun countDownSound(){
        val mediaPlayer = MediaPlayer.create(this,R.raw.countdown ) // Replace with the name of your audio file
        mediaPlayer.setOnPreparedListener {
            // The media player is prepared and ready to play
            mediaPlayer.start() // Start playing the audio
        }
    }
    fun timeIsUpSound(){
        val mediaPlayer = MediaPlayer.create(this,R.raw.wrong ) // Replace with the name of your audio file
        mediaPlayer.setOnPreparedListener {
            // The media player is prepared and ready to play
            mediaPlayer.start() // Start playing the audio
        }
    }


}