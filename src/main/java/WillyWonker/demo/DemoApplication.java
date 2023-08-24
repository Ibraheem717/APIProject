package WillyWonker.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hubspot.jinjava.Jinjava;
import okhttp3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class DemoApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);

	}
	final OkHttpClient client = new OkHttpClient();
	String cata="";
	List<Referential> lastQuestion;
	Astrology lastAstrology;
	String location;
	boolean reveal;
	boolean correct;
	final ObjectMapper objectMapper = new ObjectMapper();

	public List<Referential> run(String cata) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Request request = new Request.Builder()
				.url("https://trivia-by-api-ninjas.p.rapidapi.com/v1/trivia?category=" + cata)
				.get()
				.addHeader("X-RapidAPI-Key", "fbe8264b4cmsh1068f72e4668974p1e7f18jsn26c0cb2c5443")
				.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String responseBody = response.body().string();
				// Change to lastQuestion
                return objectMapper.readValue(responseBody, new TypeReference<List<Referential>>() {});
			}


		}
		catch (IOException e) {
			e.printStackTrace();

		}
		return new ArrayList<>();
	}

	public Astrology astrologyRun(String location) {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		this.location = location;

		Request request = new Request.Builder()
				.url("https://weatherapi-com.p.rapidapi.com/astronomy.json?q=" + location)
				.get()
				.addHeader("X-RapidAPI-Key", "fbe8264b4cmsh1068f72e4668974p1e7f18jsn26c0cb2c5443")
				.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String responseBody = response.body().string();
				// Change to Astro
                return objectMapper.readValue(responseBody, Astrology.class);
			}


		}
		catch (IOException e) {
			e.printStackTrace();

		}
		return new Astrology();
	}

	private String renderTemplate(List<Referential> arr, Astrology astro, boolean reveal, boolean correct) {
		Jinjava jinjava = new Jinjava();
		this.lastQuestion = arr;
		this.lastAstrology = astro;
		this.reveal = reveal;
		this.correct = correct;
		Map<String, Object> context = new HashMap<>();
		Map<String, String> questionContext = new HashMap<>();
		Map<String, Astrology> astrologyContext = new HashMap<>();
		for (Referential ref: arr){
			questionContext.put("question", ref.getQuestion());
			if (reveal) {
				questionContext.put("answer", ref.getAnswer());
				questionContext.put("outcome", (correct ? "Correct!" : "Wrong") );
			}
		}
		if (astro.getAstronomy()!=null) {
			astrologyContext.put("astrology", astro);
			context.put("astrology", astrologyContext);
		}
		context.put("questions", questionContext);


		String temp = null;
		try {
			temp = new String(Files.readAllBytes(Paths.get("src/main/resources/questions.html")), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return jinjava.render(temp, context);
	}

	@GetMapping("/changeLocation")
	@ResponseBody
	public String changeLocation(@RequestParam String location) throws IOException, InterruptedException {
		if (this.lastAstrology==null || this.lastQuestion==null || Objects.equals(location,""))
			return questionAPI("", location, this.reveal);
		return renderTemplate(this.lastQuestion, this.astrologyRun(location), this.reveal, this.correct);
	}

	@GetMapping("/revealAnswer")
	@ResponseBody
	public String revealAnswer(@RequestParam String answer) throws IOException, InterruptedException {
		if (this.lastAstrology==null || this.lastQuestion==null)
			return questionAPI("", this.location,false);
		if (Objects.equals(this.lastQuestion.get(0).getAnswer().toLowerCase(), answer.toLowerCase()))
			return renderTemplate(this.lastQuestion, this.lastAstrology, true, true);
		return renderTemplate(this.lastQuestion, this.lastAstrology, true, false);
	}

	@GetMapping("/refreshTrivia")
	@ResponseBody
	public String newTrivia(@RequestParam String category) throws IOException, InterruptedException {
		if (Objects.equals(category, "no_change")) {
			return questionAPI(this.cata, this.location, false);
		}
		this.cata = category;
		return questionAPI(category, this.location,false);
	}


	@GetMapping("/questions")
	@ResponseBody
	public String questionAPI(String cata, String location, boolean reveal) throws IOException, InterruptedException {
		DemoApplication example = new DemoApplication();
		if (cata==null) {
			cata = "";
		}
		if (location==null || Objects.equals(location,"")) {
			location = "London";
		}
			//
		return renderTemplate(example.run(cata), example.astrologyRun(location), reveal, false);
	}

}
