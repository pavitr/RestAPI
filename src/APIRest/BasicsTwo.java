package APIRest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.Payload;
import Files.ReUseLableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BasicsTwo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// given, when, then
				// given-all input details
				// when-submit the api resource
				// then -validate the response
		
		// Json file to string--> file need to be converted to bytecode and bytecode to string
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				String Response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
						.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\pavthrc\\addPlace.json"))))
						.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract()
						.asString();
				System.out.println(Response);
				
			
				//add place-->Update Place-->get place to validate if new address is present
				//JsonPath js=new JsonPath(Response);// for converting string to json
				JsonPath js=ReUseLableMethods.newJson(Response);
				String PlaceID=js.getString("place_id");
				String NewAddress="Sunkadakatte, bangalore";
				System.out.println("PlaceID="+PlaceID);
				
				given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + 
						"\"place_id\":\""+PlaceID+"\",\r\n" + 
						"\"address\":\""+NewAddress+"\",\r\n" + 
						"\"key\":\"qaclick123\"\r\n" + 
						"}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"))
				;
				
				//Method 1
				given().log().all().queryParam("key", "qaclick123").queryParam("place_id",PlaceID).
				when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).body("address", equalTo(NewAddress));
				
				//Method 2 by extracting address and comparing
				String Address=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",PlaceID).
				when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().asString();
				
			//	JsonPath js1=new JsonPath(Address);
				JsonPath js1=ReUseLableMethods.newJson(Address);
				String ActualAddress=js1.getString("address");
				System.out.println("Address="+ActualAddress);
				Assert.assertEquals(ActualAddress,NewAddress);

	}

}
