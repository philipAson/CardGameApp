package com.example.cardgameapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var topOfCardDeckView: ImageView
    private lateinit var topOfWastePileView: ImageView
    private lateinit var rightAnswerTextView: TextView
    private lateinit var wrongAnswerTextView: TextView

    private val images = Drawables()
    private var cardDeck = Deck()
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
        val rulesButton = findViewById<ImageButton>(R.id.rulesButton)

        higherButton.setOnClickListener {
            guessHigherOnClick()
            winCondition()
        }

        lowerButton.setOnClickListener {
            guessLowerOnclick()
            winCondition()
        }

        rulesButton.setOnClickListener {
            rulesFragment()
        }

        images.cardDrawables[firstCard.key]?.let { topOfCardDeckView.setImageResource(it) }
    }

    fun drawCard():Card {
        val card = cardDeck.drawCard()
        val image = images.cardDrawables[card.key]
        wastePile.add(0,card)

        if (image != null) {
            topOfCardDeckView.setImageResource(image)
        }
        if (wastePile.size > 1) {
            val wastePileImage = images.cardDrawables[wastePile[1].key]
            if (wastePileImage != null) {
                topOfWastePileView.setImageResource(wastePileImage)
            }
        }
        return card
    }

    fun guessHigherOnClick() {
        val card = drawCard().value
        // Got to think one step ahead when comparing (The current displayed card is already in the wastePile).
        if (card >= wastePile[1].value) {
            rightAnswer++
        } else {
            wrongAnswerLeft--
        }
        textViewUpdater()
    }
    fun guessLowerOnclick() {
        val card = drawCard().value

        if (card <= wastePile[1].value) {
            rightAnswer++
        } else {
            wrongAnswerLeft--
        }
        textViewUpdater()
    }

    @SuppressLint("SetTextI18n")
    fun textViewUpdater() {
        rightAnswerTextView.text = "Win-count: $rightAnswer"
        wrongAnswerTextView.text = "Wrong answers left: $wrongAnswerLeft"
    }

    fun winCondition() {
        if (rightAnswer >= 12 || wrongAnswerLeft <= 0) {
            val intent = Intent(this, WinLooseActivity::class.java)
            intent.putExtra("rightAnswer", rightAnswer)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        wastePile.clear()
        rightAnswer = 0
        wrongAnswerLeft = 3
        textViewUpdater()
        cardDeck.deckOfCards.clear()
        cardDeck = Deck()
        cardDeck.deckOfCards.shuffle()
        drawCard()
        Log.d("!!!", cardDeck.deckOfCards.size.toString())
    }

    fun rulesFragment() {
        val rulesFragment = supportFragmentManager.findFragmentByTag("rulesFragment")

        if (rulesFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(rulesFragment)
            transaction.commit()
        } else {
            val rulesFragment = RulesFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, rulesFragment, "rulesFragment")
            transaction.commit()

        }
    }
}