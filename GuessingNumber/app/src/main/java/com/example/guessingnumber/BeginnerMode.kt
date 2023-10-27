package com.example.guessingnumber

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TextView
import android.widget.TableRow
import kotlin.random.Random.Default.nextInt
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast

class BeginnerMode : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var  editText: EditText
    lateinit var imageButtonReset: ImageButton
    lateinit var imageButtonCheck: ImageButton
    lateinit var tableLayout: TableLayout
    private lateinit var mediaPlayer: MediaPlayer
    var number: Int = 0
    var random: Int = nextInt(1, 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begginer_mode)
        tableLayout = findViewById(R.id.tableLayout)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        imageButtonReset = findViewById(R.id.imageButtonReset)
        imageButtonCheck = findViewById(R.id.imageButtonCheck)


        textView.text= "Please enter your guess:"

        imageButtonCheck.setOnClickListener{
            if (editText.text.toString()==""){
                textView.text = "Please put a guess!!"
            }
            else {
                number = editText.text.toString().toInt()
                if (number < random) {

                    textView.text = "My number is greater than  " + number
                    editText.text.clear()
                    addGuessToHistory(number.toString())

                } else if (number > random) {
                    textView.text = "My number is less than  " + number
                    editText.text.clear()
                    addGuessToHistory(number.toString())
                } else {

                    textView.text = "Well Done!"
                    editText.text.clear()
                    successSound()
                    congratsToast()
                }
            }

        }

        imageButtonReset.setOnClickListener{

            reset()

        }

    }

    fun reset() {
        random = nextInt(1, 100)
        textView.text= "Please enter your guess:"
        editText.text.clear()
        tableLayout.removeViews(1, Math.max(0, tableLayout.getChildCount() - 1));

    }
    fun addGuessToHistory(number: String){
        val tableRow = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
        tableRow.findViewById<TextView>(R.id.numberTextView).setText(number)
        tableLayout.addView(tableRow)

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
}