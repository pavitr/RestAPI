package APIRest;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(Payload.courses());
		//Print the no of courses
		int count=js.getInt("courses.size()");
		System.out.println(count);
		//Print the purchase amount
		int TotalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(TotalAmount);
		//Print title of first course
		String FirstCourse=js.getString("courses[0].title");
		System.out.println(FirstCourse);
		
		//print all course title with their respective price
		for(int i=0; i<count; i++)
		{
			//Method 1
			/*String title=js.get("courses["+i+"].title");
			int price=js.getInt("courses["+i+"].price");
			System.out.println(title+""+price);*/
			
			//Method 2
			
			System.out.println(js.get("courses["+i+"].title").toString()+" price= "+js.get("courses["+i+"].price").toString());
		}
	

	}

}
