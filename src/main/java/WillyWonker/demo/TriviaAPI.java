package WillyWonker.demo;
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
    String category;
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
        category = "";
    }

    public boolean getReveal() {return this.reveal;}

    public List<Referential> getLastQuestion() {
        return lastQuestion;
    }

    public void getQuestionAPI()  {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        String last = (this.lastQuestion==null ? "" : this.lastQuestion.get(0).getQuestion());
        do {
            Request request = new Request.Builder()
                    .url("https://trivia-by-api-ninjas.p.rapidapi.com/v1/trivia?category=" + category)
                    .get()
                    .addHeader("X-RapidAPI-Key", DemoApplication.keyValue)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    this.lastQuestion = List.of(objectMapper.readValue(responseBody, Referential[].class));
                }
            } catch (IOException e) {
                this.lastQuestion = new ArrayList<>();
                this.lastQuestion.add(new Referential());
            }
        }
        while (Objects.equals(last, this.lastQuestion.get(0).getQuestion()));
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


    public String revealAnswer(String answer)  {
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


    public String newTrivia(String category)  {
        reveal=false;
        correct=false;
        if (!Objects.equals(category, "no_change"))
            this.category = category;
        this.getQuestionAPI();
        return renderTemplate();
    }

    public List<Referential> testQuestiontionAPI(String category)  {
        this.category=category;
        this.getQuestionAPI();
        return this.lastQuestion;
    }
}
