package APIRest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReUseLableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="booksdata")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().header("Content-Type", "application/json").body(Payload.addbook(isbn,aisle)).when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().toString();
		 JsonPath js=ReUseLableMethods.newJson(response);
		 String id=js.get("ID");
		 System.out.println(id);
	}

	@DataProvider(name="booksdata")
	public Object getData()
	{
		//array collection of elements
		//multi dimesnion array, collection of arrays
		return new Object[][] {{"abc","258"}, {"bgf","356"}, {"ghf","235"}};
	}
}
