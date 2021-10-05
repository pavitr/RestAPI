package Serialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class Serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlace a=new AddPlace();
		a.setAccuracy(50);
		a.setAddress("29, side layout, cohen 09");
		a.setLanguage("French-IN");
		a.setName("Frontline house");
		a.setPhone_number("7090879305");
		a.setWebsite("http://google.com");
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		a.setTypes(mylist);
		
		location l=new location();
		l.setLat(-23.552);
		l.setLng(-224.44);
	    a.setLocation(l);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given().log().all().queryParam("key", "qaclick123").
		body(a)
		.when().post("/maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	    System.out.println(response);

	}

}
