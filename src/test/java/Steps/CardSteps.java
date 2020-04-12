package Steps;

import Utils.CardActionHelper;
import io.cucumber.java.en.And;

public class CardSteps {

    private final CardActionHelper cardActionHelper = new CardActionHelper();

    @And("^I shuffle (a|another) deck$")
    public void shuffleDeck(String keyword) {
        cardActionHelper.shuffleDeck();
    }

    @And("^I validate that deck (has all parameters|is unique)$")
    public void shuffleDeckValidation(String keyword) {
        cardActionHelper.shuffleDeckValidation(keyword);

    }

    @And("I remember the deck number")
    public void rememberTheDeckNumber() {
        cardActionHelper.shuffleDeck();
        cardActionHelper.setDeckId();
    }

    @And("I draw '{int}' cards")
    public void drawCards(int cardCount) {
        cardActionHelper.drawCards(cardCount);
    }

    @And("I validate cards")
    public void validateCards() {
        cardActionHelper.validateCards();
    }

    @And("I validate card amount is '{int}'")
    public void validateCardAmount(int cardamount) {
        cardActionHelper.validateCardAmount(cardamount);
    }
}
