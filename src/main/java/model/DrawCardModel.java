package model;

import java.util.List;

/*
 Models the JSON response from the following API

 'https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2' API call

 as defined by 'http://deckofcardsapi.com/'

{
    "success": true,
    "cards": [
        {
            "image": "https://deckofcardsapi.com/static/img/KH.png",
            "value": "KING",
            "suit": "HEARTS",
            "code": "KH"
        },
        {
            "image": "https://deckofcardsapi.com/static/img/8C.png",
            "value": "8",
            "suit": "CLUBS",
            "code": "8C"
        }
    ],
    "deck_id":"3p40paa87x90",
    "remaining": 50
}
 */

public class DrawCardModel {
    public boolean success = false;
    public List<CardsModel> cards = null;
    public String deck_id = "";
    public int remaining = 0;
}
