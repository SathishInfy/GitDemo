package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace addPlacePayLoad(String name, String language, String address) {
		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress(address);
		a.setWebsite("http://google.com");
		a.setLanguage(language);

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		a.setLocation(location);
		return a;
	}

	public String deletePayload(String place_id) {
		return "{\r\n" + "\r\n" + "    \"place_id\":\"" + place_id + "\"\r\n" + "}\r\n" + "";
	}

}
