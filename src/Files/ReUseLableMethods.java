package Files;

import io.restassured.path.json.JsonPath;

public class ReUseLableMethods {
	// Converting the raw string to json path
	
	public static JsonPath newJson(String response)
	{
		JsonPath js1=new JsonPath(response);
		return js1;
	}

}
