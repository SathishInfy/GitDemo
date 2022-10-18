package resources;

public enum APIresources {

	AddPlaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	APIresources(String resource) {

		this.resource = resource;
	}

	private String resource;

	public String getResource() {
		return resource;
	}

}
