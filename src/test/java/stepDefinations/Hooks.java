package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//Write a code that will give Place IS
		//Execute this code when place id null
		
		StepDefination m = new StepDefination();
		
		if(StepDefination.place_id==null) {
			
		
		m.add_place_payload_with("Vishal", "Spanish", "Amravati");
		m.user_call_with_http_request("AddPlaceAPI", "POST");
		m.place_id_created_maps_to_using("Vishal", "getPlaceAPI");
	
		
		}
	}
	

}
