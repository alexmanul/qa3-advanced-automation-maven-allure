package Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardActionHelper {

    private final String CARD_DECK_API = "https://deckofcardsapi.com/api/deck/";
    private Response response;
    private String deckID;
    private int cardAmount;
    private int drawnCardAmount;
    private ValidatableResponse json;
    private RequestSpecification request;

    public void shuffleDeck() {
        response = given().get(CARD_DECK_API + "new/shuffle/?deck_count=1");
    }

    public void shuffleDeckValidation(String keyword) {
        if ("is unique".equals(keyword)) {
            Assert.assertNotSame(deckID, getCurrentDeckID());
        } else {
            response.then()
                    .statusCode(200)
                    .assertThat()
                    .body("shuffled", equalTo(true))
                    .body("success", equalTo(true))
                    .body("remaining", equalTo(52))
                    .body("deck_id", notNullValue());
            setDeckId();
        }
    }

    public void drawCards(int cardCount) {
        response = given().get(CARD_DECK_API + deckID + "/draw/?count=" + cardCount);
        setCardAmount();
        drawnCardAmount = cardCount;
    }

    public void validateCardAmount(int cardCount) {
        assertEquals(cardCount, cardAmount);
    }

    public void setDeckId() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        deckID = jsonPathEvaluator.get("deck_id");
    }

    private String getCurrentDeckID() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("deck_id");
    }

    private void setCardAmount() {
        assertEquals(deckID, getCurrentDeckID());
        JsonPath jsonPathEvaluator = response.jsonPath();
        cardAmount = jsonPathEvaluator.get("remaining");
    }

    public void validateCards() {
        response.then().statusCode(200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList cards = jsonPathEvaluator.get("cards");
        assertEquals(cards.size(), drawnCardAmount);
        for (Object card : cards) {
            assertTrue(((HashMap) card).containsKey("image"));
            assertTrue(((HashMap) card).containsKey("images"));
            assertTrue(((HashMap) card).containsKey("code"));
            assertTrue(((HashMap) card).containsKey("suit"));
            assertTrue(((HashMap) card).containsKey("value"));
        }
    }
}
