package hellocucumber;
import WillyWonker.demo.TriviaAPI;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StepDefinitions {
    private boolean output;
    private String lastQuestion;
    private String currentQuestion;
    private TriviaAPI trivia;
    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
    @Given("a question")
    public void a_question() {
        this.trivia = new TriviaAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        this.lastQuestion = this.trivia.testQuestiontionAPI("").get(0).getQuestion();
    }
    @When("I give a {string} and ask for a new question")
    public void i_give_a(String string) throws IOException {
        TriviaAPI trivia = new TriviaAPI(new String[]{"API", "questionAPI", "astrologyAPI"});
        this.currentQuestion = trivia.testQuestiontionAPI(string).get(0).getQuestion();
    }
    @Then("I should get a different question")
    public void i_should_get_a_different_question() {
        assertNotEquals(this.currentQuestion, this.lastQuestion);
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