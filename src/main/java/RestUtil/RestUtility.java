package RestUtil;

import Reporting.ExtentReport;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

public class RestUtility {
    public static RequestSpecification getRequestSpecification(String endpoint, Object payload, Map<String, String> headers) {
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload);

    }
    public static RequestSpecification getRequestSpecification(String endpoint) {
        return RestAssured.given()
                .baseUri(endpoint)
                //.headers(headers)
                .contentType(ContentType.JSON);


    }

    public static Response postMethodPayload(String endpoint, Map<String, Object> payload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, payload, headers);
        Response response = requestSpecification.post();
        requestLogInReport(requestSpecification);
        responseLogInReport(response);
        return response;
    }

    public static Response postMethodPayload(String endpoint, Object payload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, payload, headers);
        Response response = requestSpecification.post();
        requestLogInReport(requestSpecification);
        responseLogInReport(response);
        return response;
    }

    public static Response getMethod(String endpoint) {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint);
        Response response = requestSpecification.get();
        requestLogInReport(requestSpecification);
        responseLogInReport(response);


        return response;
    }


    public static void requestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReport.customizedLogsInfoDetails("Endpoint is:" + queryableRequestSpecification.getBaseUri());
        ExtentReport.customizedLogsInfoDetails("Headers are:");
        ExtentReport.customizedheadersTable(queryableRequestSpecification.getHeaders().asList());
        ExtentReport.customizedLogsInfoDetails("Method is:" + queryableRequestSpecification.getMethod());
        ExtentReport.customizedLogsInfoDetails("Body is:");
        ExtentReport.customizedLogsJSON(queryableRequestSpecification.getBody());
    }

    public static void responseLogInReport(Response response) {
        ExtentReport.customizedLogsInfoDetails("Response Status code:" + response.getStatusCode());
        ExtentReport.customizedLogsInfoDetails("Response Status Line:" + response.getStatusLine());
        ExtentReport.customizedLogsInfoDetails("Response headers are:");
        ExtentReport.customizedheadersTable(response.getHeaders().asList());
        ExtentReport.customizedLogsInfoDetails("Response Body is:");
        ExtentReport.customizedLogsJSON(response.getBody().prettyPrint());
        ExtentReport.customizedLogsInfoDetails("Response Time:" + response.getTime());
    }
}
