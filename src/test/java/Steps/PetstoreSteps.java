package Steps;

import Utils.TestProperties;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

//@Log4j
public class PetstoreSteps {

    private final String PETSTORE_API = TestProperties.getProperty("petstore.url");
    private Response response;

    @And("^I get pet information by '(.*)' id$")
    public void getPetInformationById(int id) {
        response = given().get(PETSTORE_API + "v2/pet/" + id);
    }

    @And("I create pet information with name '(.*)' and '(.*)' id")
    public void createPetInformationByIDId(String name, int id) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        JsonObject newPet = new JsonObject();
        newPet.addProperty("id", id);
        newPet.addProperty("name", name);
        newPet.addProperty("status", "available");
        httpRequest.body(newPet.toString());
        response = httpRequest.post(PETSTORE_API + "v2/pet/");
        response.then().statusCode(200);
        //log.info("Pet is created with id " + id + " and name " + name);
    }

    @And("^I delete pet information by '(.*)' id$")
    public void deletePetInformationById(int id) {
        response = given().delete(PETSTORE_API + "v2/pet/" + id);
    }

    @And("I verify pet information by '(.*)' id contains values")
    public void verifyPetInformationWithIdContainsValues(int id, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        ResponseBody body = given().get(PETSTORE_API + "v2/pet/" + id).body();
        JsonPath jp = new JsonPath(body.asString());
        //log.info(jp);

        for (Map<String, String> row : data) {
            String key = row.get("JSON_KEY");
            String expected = row.get("JSON_VALUE");
            String actual = jp.get(key).toString();
            Assert.assertEquals("Actual value from API does not match expected value", expected, actual);
        }
    }

    @And("I verify response status code is '(.*)'")
    public void verifyResponseStatusCodeIs(int expected) {
        int actual = response.getStatusCode();
        Assert.assertEquals("Response code does not match expected response code", expected, actual);
    }

    @And("I update pet information by '(.*)' id with next values")
    public void updatePetInformationByIdWithNextValues(int id, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        JsonObject pet = new JsonObject();

        for (Map<String, String> row : data) {
            String key = row.get("JSON_KEY");
            String value = row.get("JSON_VALUE");
            pet.addProperty(key, value);
        }
        httpRequest.body(pet.toString());
        //log.info(httpRequest.body(pet.toString()));

        response = httpRequest.put(PETSTORE_API + "v2/pet/");
        response.then().statusCode(200);
        //log.info("Pet with id " + id + " is updated");
    }
}



