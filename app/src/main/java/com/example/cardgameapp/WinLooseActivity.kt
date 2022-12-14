package com.example.cardgameapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class WinLooseActivity : AppCompatActivity() {

    lateinit var winOrLooseTextView: TextView
    lateinit var testImageView: ImageView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_loose)

        testImageView = findViewById(R.id.imageView3)

        val rightAnswer = intent.getIntExtra("rightAnswer", 0)

        winOrLooseTextView = findViewById(R.id.winOrLooseTextView)

        if (rightAnswer == 12) {
            winOrLooseTextView.text = "You Won!"
        } else {
            winOrLooseTextView.text = "You Loose"
        }

        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.text = "Play Again?"

        playAgainButton.setOnClickListener {

            finish()
        }
    }
}