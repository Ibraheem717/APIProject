package WillyWonker.demo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jinjava.Jinjava;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TriviaAPI {
    String catagory;
    List<Referential> lastQuestion;
    String [] listOfAPI;
    boolean reveal;
    boolean correct;
    Jinjava jinjava;
    final ObjectMapper objectMapper = new ObjectMapper();

    public TriviaAPI(String [] arr) {
        this.listOfAPI=arr;
        reveal = false;
        correct = false;
        catagory = "";
    }

    public boolean getReveal() {return this.reveal;}

    public void getQuestionAPI() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://trivia-by-api-ninjas.p.rapidapi.com/v1/trivia?category=" + catagory)
                .get()
                .addHeader("X-RapidAPI-Key", "fbe8264b4cmsh1068f72e4668974p1e7f18jsn26c0cb2c5443")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                this.lastQuestion = List.of(objectMapper.readValue(responseBody, Referential[].class));
            }
        }
        catch (IOException e) {
            assert true;
        }

    }

    public String renderTemplate() {
        jinjava = new Jinjava();
        Map<String, Object> context = new HashMap<>();
        Map<String, String> questionContext = new HashMap<>();
        for (Referential ref: lastQuestion){
            questionContext.put("question", ref.getQuestion());
            if (reveal) {
                questionContext.put("answer", ref.getAnswer());
                questionContext.put("outcome", (correct ? "Correct!" : "Wrong") );
            }
        }
        context.put("questions", questionContext);
        context.put("menu", this.listOfAPI);
        String temp = null;
        try {
            temp = Files.readString(Paths.get("src/main/resources/questions.html"));
        }
        catch (IOException e) {
            assert true;
        }

        return jinjava.render(temp, context);
    }


    public String revealAnswer(String answer) throws IOException, InterruptedException {
        if (this.lastQuestion==null) {
            this.getQuestionAPI();
        }
        else {
            reveal = true;
            if (Objects.equals(this.lastQuestion.get(0).getAnswer().toLowerCase(), answer.toLowerCase()))
                correct = true;
        }
        return renderTemplate();
    }


    public String newTrivia(String category) throws IOException, InterruptedException {
        reveal=false;
        correct=false;
        if (!Objects.equals(category, "no_change"))
            this.catagory = category;
        this.getQuestionAPI();
        return renderTemplate();
    }

    public List<Referential> testQuestiontionAPI(String catagory) throws IOException {
        this.catagory=catagory;
        this.getQuestionAPI();
        return this.lastQuestion;
    }
}
