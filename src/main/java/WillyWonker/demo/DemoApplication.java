package WillyWonker.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hubspot.jinjava.Jinjava;
import okhttp3.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
	String cata = "";
	final ObjectMapper objectMapper = new ObjectMapper();

	public List<Referential> run(String cata) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		System.out.println("https://trivia-by-api-ninjas.p.rapidapi.com/v1/trivia?category=" + cata);
		Request request = new Request.Builder()
				.url("https://trivia-by-api-ninjas.p.rapidapi.com/v1/trivia?category=" + cata)
				.get()
				.addHeader("X-RapidAPI-Key", "fbe8264b4cmsh1068f72e4668974p1e7f18jsn26c0cb2c5443")
				.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String responseBody = response.body().string();
				List<Referential> referentialList = objectMapper.readValue(responseBody, new TypeReference<List<Referential>>() {});
				return referentialList;
			}


		}
		catch (IOException e) {
			e.printStackTrace();

		}
		return new ArrayList<>();
	}

	private static String renderTemplate(List<Referential> arr) {
		Jinjava jinjava = new Jinjava();
		Map<String, Object> context = new HashMap<>();
		context.put("arr", arr);

		String temp = null;
		try {
			temp = new String(Files.readAllBytes(Paths.get("src/main/resources/questions.html")), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(context.get("arr"));
		return jinjava.render(temp, context);
	}

	@GetMapping("/refreshTrivia")
	@ResponseBody
	public String newTrivia(@RequestParam String category) throws IOException, InterruptedException {
		if (Objects.equals(category, "no_change")) {
			return questionAPI(this.cata);
		}
		this.cata = category;
		return questionAPI(category);
	}


	@GetMapping("/questions")
	@ResponseBody
	public String questionAPI(String cata) throws IOException, InterruptedException {
		DemoApplication example = new DemoApplication();
		return renderTemplate(example.run(cata));
	}

}
