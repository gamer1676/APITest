package common;

import org.testng.Assert;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/*
 Wrapper class that calls the DeckOfCardsApi.com API by inheriting the TestAPIClient
 */
public class DeckOfCardWebService extends TestAPIClient {

    public DeckOfCardWebService() { }

    /*
     Calls the 'new deck' API
     */
    public String GetNewDeckWithJokers() {
        Map<Object, Object> jokerParameter = new HashMap<>();
        jokerParameter.put("jokers_enabled", "true");

        return POST("https://deckofcardsapi.com/api/deck/new/", jokerParameter);
    }

    /*
     Calls the 'Draw' API with the specified number of cards to draw
     */
    public String DrawXNumberOfCard(String deck_id, int numberOfCards) {
        return GET("https://deckofcardsapi.com/api/deck/"
                + deck_id + "/draw/?count=" + numberOfCards);
    }
}
