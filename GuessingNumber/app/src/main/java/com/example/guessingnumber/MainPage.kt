package com.example.guessingnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainPage : AppCompatActivity() {
    lateinit var begModeButton: Button
    lateinit var expModeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        val intentBeg = Intent(this, BeginnerMode::class.java)
        val intentExp = Intent(this, ExpertMode::class.java)
        begModeButton= findViewById(R.id.beginner_Mode)
        expModeButton= findViewById(R.id.expert_Mode)
        begModeButton.setOnClickListener{
            startActivity(intentBeg)
        }
        expModeButton.setOnClickListener {
            startActivity(intentExp)
        }
    }
}