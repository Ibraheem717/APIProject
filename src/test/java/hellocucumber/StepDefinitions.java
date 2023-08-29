package hellocucumber;

import WillyWonker.demo.TriviaAPI;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsItFriday {
    static String isItFriday(String today) {
        return "Friday".equals(today) ? "True" : "False";
    }
}

public class StepDefinitions {
    private boolean output;
    @Given("quiz takes a catagory")
    public void quiz_takes_a_catagory() {

    }
    @When("I give a {string}")
    public void i_give_a(String string) throws IOException {
        TriviaAPI trivia = new TriviaAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        this.output = trivia.testQuestiontionAPI(string).isEmpty();
    }
    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
    @Then("I should get {string}")
    public void i_should_get(String string) throws IOException {
        assertEquals(booleanValue(string), this.output);
    }


//    @Given("today is {string}")
//    public void today_is_Sunday(String string) {
//        today = string;
//    }
//
//    @When("I ask whether it's Friday yet")
//    public void i_ask_whether_it_s_Friday_yet() {
//        actualAnswer = IsItFriday.isItFriday(today);
//    }
//
//    @Then("I should be told {string}")
//    public void i_should_be_told(String expectedAnswer) {
//        assertEquals(expectedAnswer, actualAnswer);
//    }
}