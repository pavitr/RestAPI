package APIRest;
import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OauthTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//From 2020 google has stopped automatic Login
	/*	System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver1.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("pavithrac582@gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pavi@1996");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);*/
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWiUdQVwBA-Cc2OIBBY1OCkGyPhXmj62AVagAblUTSJDsNCdB1i1k66-6BSejkITKw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		String Partialcode=url.split("code=")[1];
		String code=Partialcode.split("&scope")[0];
		System.out.println("code===>"+code);
		
		String AccessToken=given().
				urlEncodingEnabled(false).// to make sure % in code will not be converted by rest assured
		queryParam("code", code).
		queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
		queryParam("grant_type", "authorization_code").
		when().log().all().
		post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=new JsonPath(AccessToken);
		String responseOfAccess=js.get("access_token");
		
		String Response=given().queryParam("access_token", responseOfAccess).
		when().log().all().
		get("https://rahulshettyacademy.com/getCourse.php")
		.asString();// shortcut to raw string reponse
		System.out.println(Response);
		
		
		

	}

}
