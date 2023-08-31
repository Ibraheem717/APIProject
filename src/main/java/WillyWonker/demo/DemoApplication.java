package WillyWonker.demo;
import com.hubspot.jinjava.Jinjava;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class DemoApplication {
	private String [] listOfAPI = {"API", "questionAPI", "astrologyAPI"};
	static String keyValue=System.getenv("RAPID_API_KEY");
	private TriviaAPI trivia = new TriviaAPI(listOfAPI);
	private AstrologyAPI astrology = new AstrologyAPI(listOfAPI);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
	}
	public TriviaAPI getTrivia() {
		return trivia;
	}
	public AstrologyAPI getAstrology() {
		return astrology;
	}

	@GetMapping("/questionAPI")
	@ResponseBody
	public String questionAPI()  {
		this.trivia.getQuestionAPI();
		return this.trivia.renderTemplate();
	}
	@GetMapping("/revealAnswer")
	@ResponseBody
	public String revealAnswer(@RequestParam String answer)  {
		return this.trivia.revealAnswer(answer);
	}

	@GetMapping("/refreshTrivia")
	@ResponseBody
	public String newTrivia(@RequestParam String category)  {
		return this.trivia.newTrivia(category);
	}

	@GetMapping("/astrologyAPI")
	@ResponseBody
	public String astrologyAPI()  {
		this.astrology.getAstrologyAPI();
		return this.astrology.renderTemplate();
	}
	@GetMapping("/removeLocation")
	@ResponseBody
	public String removeLocation(@RequestParam String location)  {
		this.astrology.removeLocation(location);
		return this.astrology.renderTemplate();
	}
	@GetMapping("/addLocation")
	@ResponseBody
	public String addLocation(@RequestParam String location)  {
		this.astrology.addLocation(location);
		return this.astrology.renderTemplate();
	}
	@GetMapping("/API")
	@ResponseBody
	public String homePage()  {
		Jinjava jinjava = new Jinjava();
		String temp = null;
		try {
			temp = Files.readString(Paths.get("src/main/resources/Home.html"));
		}
		catch (IOException e) {
			assert true;
		}
		return jinjava.render(temp,  new HashMap<>() );
	}

}
