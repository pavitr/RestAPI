package APIRest;

import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;


public class SumValidation {

	@Test
	public void SumValid()
	{
		int sum=0;
		JsonPath js=new JsonPath(Payload.courses());
		//Print the no of courses
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		for(int i=0;i<count; i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount=price*copies;
			System.out.println(amount);
			sum=sum+amount;
		}
		
		System.out.println(sum);
		int total=js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(total, sum);
		
		
	}
}
