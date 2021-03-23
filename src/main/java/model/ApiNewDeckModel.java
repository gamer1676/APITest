package model;

/*
 Models the JSON response from the following API

 'https://deckofcardsapi.com/api/deck/new/'

 as defined by https://deckofcardsapi.com

 {
    "success": true,
    "deck_id": "3p40paa87x90",
    "shuffled": false,
    "remaining": 52
 }
 */

public class ApiNewDeckModel {
    public boolean success = false;
    public String deck_id = "";
    public boolean shuffled = false;
    public int remaining = 0;
}
