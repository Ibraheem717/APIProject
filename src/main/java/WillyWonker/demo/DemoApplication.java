package WillyWonker.demo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hubspot.jinjava.Jinjava;
import okhttp3.*;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.Yaml;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class DemoApplication {
	String [] listOfAPI = {"API", "questionAPI", "astrologyAPI"};
	static String keyValue=System.getenv("RAPID_API_KEY");
	TriviaAPI trivia = new TriviaAPI(listOfAPI);
	AstrologyAPI astrology = new AstrologyAPI(listOfAPI);
	Jinjava jinjava;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/questionAPI")
	@ResponseBody
	public String questionAPI() throws IOException, InterruptedException {
		this.trivia.getQuestionAPI();
		return this.trivia.renderTemplate();
	}
	@GetMapping("/revealAnswer")
	@ResponseBody
	public String revealAnswer(@RequestParam String answer) throws IOException, InterruptedException {
		return this.trivia.revealAnswer(answer);
	}

	@GetMapping("/refreshTrivia")
	@ResponseBody
	public String newTrivia(@RequestParam String category) throws IOException, InterruptedException {
		return this.trivia.newTrivia(category);
	}

	@GetMapping("/astrologyAPI")
	@ResponseBody
	public String astrologyAPI() throws IOException, InterruptedException {
		this.astrology.getAstrologyAPI();
		return this.astrology.renderTemplate();
	}
	@GetMapping("/removeLocation")
	@ResponseBody
	public String removeLocation(@RequestParam String location) throws IOException, InterruptedException {
		this.astrology.removeLocation(location);
		return this.astrology.renderTemplate();
	}
	@GetMapping("/addLocation")
	@ResponseBody
	public String addLocation(@RequestParam String location) throws IOException, InterruptedException {
		this.astrology.addLocation(location);
		return this.astrology.renderTemplate();
	}
	@GetMapping("/API")
	@ResponseBody
	public String homePage() throws IOException, InterruptedException {
		this.jinjava = new Jinjava();
		String temp = null;
		try {
			temp = Files.readString(Paths.get("src/main/resources/Home.html"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return jinjava.render(temp,  new HashMap<>() );
	}

}
