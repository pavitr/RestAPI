package APIRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import Files.Payload;
import Files.ReUseLableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BasicsOne {

	public static void main(String[] args) {
		// given, when, then
		// given-all input details
		// when-submit the api resource
		// then -validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.Details())
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
