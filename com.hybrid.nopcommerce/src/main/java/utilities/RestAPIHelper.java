package utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPIHelper {
	public static RestAPIHelper initAPI(){
		return new RestAPIHelper();
	}
	
	public RequestSpecification createRequestSpecification() {
		RequestSpecification spec = RestAssured.given().header("Content-Type", "application/json");
		return spec;
	}
	
	public Response runGetMethod(RequestSpecification spec, String uri) {
		return spec.get(uri);
	}
	
	public Response runPostMethod(RequestSpecification spec, String uri) {
		return spec.post(uri);
	}
	
	public Response runPutMethod(RequestSpecification spec, String uri) {
		return spec.put(uri);
	}
	
	public Response runDeleteMethod(RequestSpecification spec, String uri) {
		return spec.delete(uri);
	}
	
	public int getStatusCode(Response res) {
		return res.getStatusCode();
	}
	
	public String getResponseValue(Response res, String jsonPath) {
		return res.then().extract().path(jsonPath);
	}
}
