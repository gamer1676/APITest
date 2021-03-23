import com.google.gson.Gson;
import common.DeckOfCardWebService;
import model.ApiNewDeckModel;
import model.DrawCardModel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeckOfCardUnitTest {
    final int JOKERDECK = 54;

    DeckOfCardWebService webService = null;
    Gson gson = null;

    @BeforeTest
    public void TestInitialize() {
        // initialize all the common test objects when we load the tests so they are in clean state
        webService =  new DeckOfCardWebService();
        gson = new Gson();
    }

    /*
    Atomic test to get a new deck of card including 2 jokers
     */
    @Test
    public void ValidateNewDeckWithJoker()    {
        // call API
        String result = webService.GetNewDeckWithJokers();

        // assert that call was successful (ideally this should be handled by framework exceptions)
        Assert.assertNotEquals(result, "", "There was an exception during NewDeck API call");

        // serialize result into NewDeckModel
        ApiNewDeckModel apiResult = gson.fromJson(result, ApiNewDeckModel.class);

        // assert values of a joker deck
        Assert.assertEquals(apiResult.success, true,
                "API call was not successfull");
        Assert.assertEquals(apiResult.remaining, JOKERDECK,
                "Deck doesn't have [" + JOKERDECK + "] cards");   // 54 because of 2 jokers
        Assert.assertEquals(apiResult.shuffled, false,
                "Deck should not be shuffled");
    }

    /*
    Atomic test to get a new deck of card including 2 jokers and drawing 50 cards.
     */
    @Test
    public void ValidateDrawingFiftyCardFromJokerDeck() {
        int numberOfCardsToDraw = 50;

        // draw a new deck
        String result = webService.GetNewDeckWithJokers();

        // assert that call was successful (ideally this should be handled by framework exceptions)
        Assert.assertNotEquals(result, "", "There was an exception during NewDeck API call");

        // serialize result into NewDeckModel
        ApiNewDeckModel apiResult = gson.fromJson(result, ApiNewDeckModel.class);

        // assert values of a joker deck
        Assert.assertEquals(apiResult.success, true,
                "API call was not successfull");
        Assert.assertEquals(apiResult.remaining, JOKERDECK,
                "Deck doesn't have [" + JOKERDECK + "] cards");   // 54 because of 2 jokers
        Assert.assertEquals(apiResult.shuffled, false,
                "Deck should not be shuffled");

        // draw 'numberOfCardsToDraw' from deck
        result = webService.DrawXNumberOfCard(apiResult.deck_id, numberOfCardsToDraw);
        Assert.assertNotEquals(result, "", "There was an exception during DrawCard API call");

        // serialize result into DrawCardModel
        DrawCardModel drawCardResult = gson.fromJson(result, DrawCardModel.class);

        // assert values of results
        Assert.assertEquals(drawCardResult.success, true,
                "API call was not successful");
        Assert.assertEquals(drawCardResult.cards.size(), numberOfCardsToDraw,
                "We did not draw [" + numberOfCardsToDraw + "] cards");
        Assert.assertEquals(drawCardResult.deck_id, apiResult.deck_id,
                "Deck ID should remain the same");
        Assert.assertEquals(drawCardResult.remaining, apiResult.remaining-numberOfCardsToDraw,
                "Deck does not have correct number of cards");
    }
}
