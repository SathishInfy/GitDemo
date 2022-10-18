package stepDefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import java.io.IOException;

import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	ResponseSpecification resSpec;
	RequestSpecification resp;
	Response res;
	static String place_id;

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		resp = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIresources resourceAPI = APIresources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if (method.equalsIgnoreCase("POST"))
			res = resp.when().post(resourceAPI.getResource());
		if (method.equalsIgnoreCase("GET"))
			res = resp.when().get(resourceAPI.getResource());
		if (method.equalsIgnoreCase("Delete"))
			res = resp.when().delete(resourceAPI.getResource());
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(200, res.getStatusCode());

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {

		assertEquals(getJsonPath(res, keyValue), Expectedvalue);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
		place_id = getJsonPath(res, "place_id");
		resp = given().spec(requestSpecification()).queryParam("place_id", place_id);

		user_calls_with_http_request(resource, "GET");

		String ActualName = getJsonPath(res, "name");
		assertEquals(ActualName, ExpectedName);
	}

	@Given("Delete Payload")
	public void delete_payload() throws IOException {
		resp = given().spec(requestSpecification()).body(data.deletePayload(place_id));
	}

}
