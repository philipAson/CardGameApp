package com.example.cardgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var topOfCardDeckView: ImageView
    private lateinit var topOfWastePileView: ImageView
    private lateinit var rightAnswerTextView: TextView
    private lateinit var wrongAnswerTextView: TextView

    private val images = Drawables()
    private val cardDeck = Deck()
    private var wastePile = mutableListOf<Card>()

    var rightAnswer = 0
    var wrongAnswerLeft = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardDeck.deckOfCards.shuffle()

        val firstCard = cardDeck.drawCard()
        wastePile.add(firstCard)

        topOfCardDeckView = findViewById(R.id.imageView)
        topOfWastePileView = findViewById(R.id.imageView2)
        rightAnswerTextView = findViewById(R.id.rightCountView)
        wrongAnswerTextView = findViewById(R.id.wrongCountView)

        textViewUpdater()

        val higherButton = findViewById<Button>(R.id.hugherButton)
        val lowerButton = findViewById<Button>(R.id.lowerButton)

        higherButton.setOnClickListener {
            guessHigherOnClick()
        }
        lowerButton.setOnClickListener {
            guessLowerOnclick()
        }
        images.cardDrawables[firstCard.key]?.let { topOfCardDeckView.setImageResource(it) }
    }

    fun drawCard():Card {
        var card = cardDeck.drawCard()
        var image = images.cardDrawables[card.key]
        wastePile.add(0,card)

        if (image != null) {
            topOfCardDeckView.setImageResource(image)
        }
        if (wastePile.size > 1) {
            var wastePileImage = images.cardDrawables[wastePile[1].key]
            if (wastePileImage != null) {
                topOfWastePileView.setImageResource(wastePileImage)
            }
        }
        return card
    }

    fun guessHigherOnClick() {
        var card = drawCard().value
        // Got to think one step ahead when comparing (The current displayed card is already in the wastePile).
        if (card >= wastePile[1].value) {
            rightAnswer++
        } else {
            wrongAnswerLeft--
        }
        textViewUpdater()
    }
    fun guessLowerOnclick() {
        var card = drawCard().value

        if (card <= wastePile[1].value) {
            rightAnswer++
        } else {
            wrongAnswerLeft--
        }
        textViewUpdater()
    }

    fun textViewUpdater() {
        rightAnswerTextView.text = "Wincount: $rightAnswer"
        wrongAnswerTextView.text = "Wrong answers left: $wrongAnswerLeft"
    }

    /*fun winCondition() {

    }*/
}