package stepDefination.rest;

import io.cucumber.java.en.*;
import io.cucumber.messages.internal.com.google.gson.JsonArray;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.junit.Assert;
import utility.RestUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestOperations extends RestUtility {


    Response response;

    @Given("User triggers {string} Request to get list of users")
    public void userTriggersRequestToGetListOfUsers(String arg0) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authentication",authentication(getProperty("adminUsername"),getProperty("password")));
        headers.put("Content-Type","application/json");


        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("page","2");

        response  = callAPIMethod("/api/users",parameters,headers,getProperty("endpoint"),arg0,null);

    }


    @Then("Validates the status code of the response as {int}")
    public void validatesTheStatusCodeOfTheResponseAs(int arg0) throws Exception {
        scenario.log("response :-->" +response.getBody().prettyPrint());
        statusValidator(arg0);
    }

    @Then("Validates the content type of the response as {string}")
    public void validatesTheContentTypeOfTheResponseAs(String arg0) throws Exception {
        contentTypeValidator(arg0);
    }


    @Then("Validates the list of users returned in the response are greater than {int}")
    public void validatesTheListOfUsersReturnedInTheResponseAreGreaterThan(int arg0) {

        //scenario.log("response :-->" +response.getBody().prettyPrint());
        ArrayList jsonArrayList= response.getBody().jsonPath().getJsonObject("data");
        Assert.assertTrue(jsonArrayList.size()>5);

    }


    @Given("User triggers {string} Request to Single user with ID {int}")
    public void userTriggersRequestToSingleUserWithID(String arg0, int arg1) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authentication",authentication(getProperty("adminUsername"),getProperty("password")));
        headers.put("Content-Type","application/json");



        response  = callAPIMethod("/api/users/"+arg1,null,headers,getProperty("endpoint"),"GET",null);

    }

    @Then("Validates that valid user ID {int} is returned in the response")
    public void validatesThatOnlyOneUserIsReturnedInTheResponse(int arg0) {
        //scenario.log("response :-->" +response.getBody().prettyPrint());
        int id= response.getBody().jsonPath().get("data.id");
        Assert.assertTrue(id==arg0);

    }

    @Given("User triggers {string} Request with <{string}>  and <{string}>")
    public void userTriggersRequestWithAnd(String arg0, String arg1, String arg2) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authentication",authentication(getProperty("adminUsername"),getProperty("password")));
        headers.put("Content-Type","application/json");

        Map<String, String> body = new HashMap<String, String>();
        body.put("name",arg1);
        body.put("job",arg2);

        response  = callAPIMethod("/api/users/",null,headers,getProperty("endpoint"),"POST",body);

    }

    @Then("Validates that valid <{string}>  and <{string}> is returned in the response")
    public void validatesThatValidAndIsReturnedInTheResponse(String arg0, String arg1) {
        //scenario.log("response :-->" +response.getBody().prettyPrint());
        String name= response.getBody().jsonPath().get("name");
        String job= response.getBody().jsonPath().get("job");

        Assert.assertTrue(name.equals(arg0));
        Assert.assertTrue(job.equals(arg1));
    }


    @Given("User triggers {string} Request for <{string}> with <{string}>  and <{string}>")
    public void userTriggersRequestForWithAnd(String arg0, String arg1, String arg2, String arg3) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authentication",authentication(getProperty("adminUsername"),getProperty("password")));
        headers.put("Content-Type","application/json");

        Map<String, String> body = new HashMap<String, String>();
        body.put("name",arg2);
        body.put("job",arg3);

        response  = callAPIMethod("/api/users/"+arg1,null,headers,getProperty("endpoint"),arg0,body);

    }

    @Given("User triggers {string} Request with <{string}>  and <{string}> for Registering user")
    public void userTriggersRequestWithAndForRegisteringUser(String arg0, String arg1, String arg2) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authentication",authentication(getProperty("adminUsername"),getProperty("password")));
        headers.put("Content-Type","application/json");

        Map<String, String> body = new HashMap<String, String>();
        body.put("email",arg1);
        body.put("password",arg2);

        response  = callAPIMethod("/api/register/",null,headers,getProperty("endpoint"),arg0,body);

    }
    @Then("Validates that valid id and token are returned in the response")
    public void validatesThatValidAndAreReturnedInTheResponse() {
        String id= response.getBody().jsonPath().get("id");
        String token= response.getBody().jsonPath().get("token");

        Assert.assertTrue(id!=null);
        Assert.assertTrue(token!=null);

    }
    @Given("User triggers {string} Request for user with {string}")
    public void userTriggersRequestForUserWith(String arg0, String arg1) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authentication",authentication(getProperty("adminUsername"),getProperty("password")));
        headers.put("Content-Type","application/json");

        response  = callAPIMethod("/api/users/"+arg1,null,headers,getProperty("endpoint"),arg0,null);
    }

    @Then("Validates that error message {string}")
    public void validatesThatErrorMessage(String arg0) {
        String errorMessage= response.getBody().jsonPath().get("error");
        Assert.assertTrue(errorMessage.equals(arg0));
    }

}
