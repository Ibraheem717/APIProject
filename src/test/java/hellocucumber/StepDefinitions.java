package hellocucumber;
import WillyWonker.demo.TriviaAPI;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private boolean output;
    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
    @Given("quiz takes a catagory")
    public void quiz_takes_a_catagory() {
    }
    @When("I give a {string}")
    public void i_give_a(String string) throws IOException {
        TriviaAPI trivia = new TriviaAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        this.output = trivia.testQuestiontionAPI(string).isEmpty();
    }
    @Given("the question isn't answered already")
    public void the_question_isn_t_answered_already() {
    }
    @When("I give an {string}")
    public void i_give_an(String string) throws IOException, InterruptedException {
        TriviaAPI trivia = new TriviaAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        trivia.getQuestionAPI();
        trivia.revealAnswer(string);
        this.output = trivia.getReveal();
    }
    @Then("I should get {string}")
    public void i_should_get(String string) throws IOException {
        assertEquals(booleanValue(string), this.output);
    }

}