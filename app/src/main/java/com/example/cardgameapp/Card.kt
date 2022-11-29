package com.example.cardgameapp

// Attributes Card Class

// 1 Value. 2 Suit. 3 Colour. 4 FaceDown/Up. 5 Drawable.
const val clubs = "Clubs"
const val diamonds = "Diamonds"
const val hearts = "Hearts"
const val spades = "Spades"

class Card (val value: Int, val suit: String, val key: Int) {

    override fun toString(): String = "$value $suit $key"
}