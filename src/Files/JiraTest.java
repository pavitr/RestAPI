package Files;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {
	
	public static void main(String[] args)
	{
		RestAssured.baseURI="http://localhost:8082";
		
		//to avaoid extract using json path this is simple way
		SessionFilter session=new SessionFilter();
		
		//for cookie based authentication
		//to bypass validation if its an https website then we can use relaxedhttpsvalidation
		
		String Response=given().relaxedHTTPSValidation().header("Content-Type", "application/json").body("{ \"username\": \"pavthrc\", \"password\": \"123456@Pc\" }")
		.log().all().filter(session).when().post("rest/auth/1/session").then().log().all().extract().response().asString();
		
		//to add the comment 
		
		String addCommentResponse=given().pathParam("id", "10100").log().all().header("Content-Type", "application/json").body("{\r\n" + 
				"    \"body\": \"this is my first comment.\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js=new JsonPath(addCommentResponse);
		String CommentID=js.getString("id");
		
		//to add the attachments/*multipart*/
		given().header("X-Atlassian-Token","no-check").log().all().filter(session).pathParam("id", "10100")
		.header("Content-Type", "multipart/form-data")
		.multiPart("file", new File("jira.txt")).
		when().post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
		
		
		String issue=given().filter(session).pathParam("id", "10100")
				.queryParam("fields", "comment")
				.when().get("/rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		System.out.println(issue);
		
		JsonPath js1=new JsonPath(issue);
		int commentCount=js1.getInt("fields.comment.comments.size()");
		
		System.out.println(commentCount);
		
		for(int i=0;i<commentCount; i++)
		{
			System.out.println(js.getInt("fields.comment.comments.size["+i+"].id"));
			
		}
		
		
		
		
	}

}
