package com.example.cardgameapp
// Attributes Deck Class.

// 1 Generate a Deck of (Card)'s [Array<Card>] with 52 cards 1->13 x 4.
// 2 Reset and Shuffle Cards in the Deck.
// 3 Draw a Card from the Deck.
// 4 Add card to a waste pile.

class Deck {
    private var key = -1
    // Anonymous function.
    private val setValueAndSuitAndKey = fun (i: Int): Card {
        // value equals the remainder when (index) -> [i] is (modulus) [%] by 13
        val value = i % 13
        val suit = when(i / 13) {
            0 -> clubs
            1 -> diamonds
            2 -> hearts
            else -> spades
        }
        key ++
        return Card(value, suit, key)
    }
    /* Arrays are static lists and is therefore not suited for a DeckOfCards.
    So we create a (List) containing deckOfCards-array, thus we can shuffle and draw cards from it */
    private val cards: Array<Card> = Array(52, setValueAndSuitAndKey)
    var deckOfCards: MutableList<Card> = cards.toMutableList()

    fun drawCard(): Card {
        var card = deckOfCards.removeAt(0)
        card.faceUp = true
        return card
    }
}