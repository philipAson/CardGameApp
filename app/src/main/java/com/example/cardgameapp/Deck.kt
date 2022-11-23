package com.example.cardgameapp
// Attributes Deck Class.

// 1 Generate a Deck of (Card)'s [Array<Card>] with 52 cards 1->13 x 4.
// 3 Draw a Card from the Deck.

class Deck {
    // Anonymous function.

    // gets its index from Array position
    private val setValueAndSuitAndKey = fun (i: Int): Card {
        // value equals the remainder when (index) -> [i] is (modulus) [%] by 13
        val value = i % 13
        /* suit equals "how many times you can divide [i] with 13"
        since [i] is an int it always returns a noDecimal number */
        val suit = when(i / 13) {
            0 -> clubs
            1 -> diamonds
            2 -> hearts
            else -> spades
        }
        /*key is not defined in the function,
        since its value is set by the position[i] in the Array*/
        return Card(value, suit, i)
    }
    /* Arrays are static lists and is therefore not suited for a DeckOfCards.
    So we create a (List) containing deckOfCards-array,
    thus we can shuffle and draw cards from it */

    // SetValueAndSuitAndKey Iterates 52 times
    private val cards: Array<Card> = Array(52, setValueAndSuitAndKey)
    // Convert to mutableList
    var deckOfCards: MutableList<Card> = cards.toMutableList()

    fun drawCard(): Card {
        return deckOfCards.removeAt(0)
    }
}