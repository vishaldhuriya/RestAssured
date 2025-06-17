package resources;

import java.util.ArrayList;
import java.util.List;

import POJO_Serialization.AddPlace;
import POJO_Serialization.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		
		AddPlace p =new AddPlace();
		p.setAccuracy(101);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("9011851736");
		p.setWebsite("https://rahulshettyacademy.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("Shop");
		myList.add("Shop Park");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-34.765775);
		l.setLng(34.12345);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String PlaceId)
	{
		return "{\\r\\n   \\\"place_id\\\": \\\""+PlaceId+"\\\"\\r\\n}";
	}

}
