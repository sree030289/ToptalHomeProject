package utility;

import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RestUtility {
    private Response response;
    public Scenario scenario;


     static Properties prop = new Properties();

    public RestUtility() {
        this.scenario = DriverFactory.getInstance().getScenario();
    }

    public static void loadPropertyFile() {

        try {

            String path =  Utility.class.getClass().getResource("/config/configuration.properties").getFile();

            prop.load(new FileInputStream(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static String getProperty(String key) {

        loadPropertyFile();
        return prop.getProperty(key);

    }

    public String authentication(String clientID, String clientSecret)
    {
        Base64 b = new Base64();
        return "Basic " + b.encodeAsString((new String(clientID + ":" + clientSecret)).getBytes());
    }
    public Response callAPIMethod
            (String pathParams, Map<String, String> parameters, Map<String, String> headers,String endPoint, String apiMethod, Map<String, String> requestBody) throws Exception
    {

        requestBody = validateMapdata(requestBody);
        parameters = validateMapdata(parameters);
        headers = validateMapdata(headers);

        createLogs(pathParams, parameters, headers, endPoint, apiMethod, requestBody);


        RestAssured.baseURI = endPoint;
        switch (validateApiMethod(apiMethod)) {
            case GET:
                response = getRequest(pathParams, headers, parameters, requestBody);
                break;
            case POST:
                response = postRequest(pathParams, headers, parameters, requestBody);
                break;
            case PUT:
                response = putRequest(pathParams, headers, parameters, requestBody);
                break;
            case DELETE:
                response = deleteRequest(pathParams, headers, parameters, requestBody);
                break;
            case PATCH:
                response = patchRequest(pathParams, headers, parameters, requestBody);
                break;

            default:
                throw new Exception("API CALL " + apiMethod
                        + " is not valid and not supported. Supported values are " + Arrays.asList(RestMethods.values()),
                        null);
        }


        if (StringUtils.isNotBlank(response.asString())) {
            scenario.log("Response");
        }
        return response;
    }

    public void statusValidator(Integer statusCode) throws Exception {

        Integer actualStatusCode = response.getStatusCode();
        if (actualStatusCode.equals(statusCode)) {
            scenario.log("Executed successfully with status code " + statusCode);
        } else {
            scenario.log("Expected status code " + statusCode + " but was " + actualStatusCode);
            throw new Exception("Validation Failed: Expected status code " + statusCode + " but was " + actualStatusCode, null);
        }


    }

    public void contentTypeValidator(String  contentType) throws Exception {

        String actualContentType = response.getContentType();

        if (actualContentType.contains(contentType)) {
            scenario.log("Executed successfully with contentType as " + contentType);
        } else {
            scenario.log("Expected contentType " + contentType + " but was " + actualContentType);
            throw new Exception("Validation Failed: Expected content Type" + contentType + " but was " + actualContentType, null);
        }

    }

    private Response getRequest(String pathParams, Map<String, String> headers, Map<String, String> queryParams,
                                Map<String, String> requestBody) throws Exception {
        try {

            return given().relaxedHTTPSValidation().urlEncodingEnabled(true).headers(headers).queryParams(queryParams)
                    .body(requestBody).when().get(pathParams);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), null);
        }
    }

    private Response postRequest(String pathParams, Map<String, String> headers, Map<String, String> queryParams,
                                 Map<String, String> requestBody) throws Exception {
        try {
            return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).queryParams(queryParams)
                    .body(requestBody).post(pathParams);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), null);
        }
    }

    private Response patchRequest(String pathParams, Map<String, String> headers, Map<String, String> queryParams,
                                  Map<String, String> requestBody) throws Exception {
        try {
            return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).queryParams(queryParams)
                    .body(requestBody).when().patch(pathParams);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), null);
        }
    }

    private Response putRequest(String pathParams, Map<String, String> headers, Map<String, String> queryParams,
                                Map<String, String> requestBody) throws Exception {
        try {
            return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).queryParams(queryParams)
                    .body(requestBody).when().put(pathParams);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), null);
        }
    }

    private Response deleteRequest(String pathParams, Map<String, String> headers, Map<String, String> queryParams,
                                   Map<String, String> requestBody) throws Exception {
        try {
            return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).queryParams(queryParams)
                    .body(requestBody).when().delete(pathParams);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), null);
        }
    }

    private RestMethods validateApiMethod(String apiMethod) throws Exception {
        if (StringUtils.isBlank(apiMethod)) {
            throw new Exception("Provide a API CALL. Supported values are " + RestMethods.values(), null);
        }
        for (RestMethods rm : RestMethods.values()) {
            if (apiMethod.trim().equals(rm.toString()))
                return rm;
        }
        throw new Exception("API CALL " + apiMethod + " is not valid and not supported. Supported values are "
                + Arrays.asList(RestMethods.values()), null);

    }



    @SuppressWarnings("unchecked")
    private Map<String, String> validateMapdata(Map<String, String> data) {
        if (data == null) {
            return new HashMap<String, String>();
        }
        return data;
    }


    public void createLogs(String pathParams, Map<String, String> parameters, Map<String, String> headers,
                           String endPoint, String apiMethod, Map<String, String> requestBody) {

        if (StringUtils.isNotBlank(apiMethod)) {
            scenario.log("API Method : " + apiMethod);
        }

        if (StringUtils.isNotBlank(endPoint)) {
            scenario.log("EndPoint : " + endPoint);
        }

        if (StringUtils.isNotBlank(pathParams)) {
            scenario.log("Path Params : " + pathParams);
        }

        if (parameters != null && !parameters.isEmpty()) {
            scenario.log("Parameters : " + parameters);
        }

        if (headers != null && !headers.isEmpty()) {
            scenario.log("Headers : " + headers);
        }

        if (requestBody!= null && !requestBody.isEmpty()) {
            scenario.log("Request Body");
        }

    }


}
