package UserDetails;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.User;

import java.io.IOException;
import java.util.Map;

public class UserClass {
    UserApiReader userApiReader = new UserApiReader();

    @Test()
    public void createUser() throws IOException {
        Map<String, Object> payload = UserPayload.createPayloadUsingMap();
        Response response = userApiReader.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

// Using JSONPATH to compare response body
    @Test
    public void createUserAndVerifyResponse() throws IOException {
        Map<String, Object> payload = UserPayload.createPayloadUsingMap();
        Response response = userApiReader.createUser(payload);
        Assert.assertEquals(response.jsonPath().getString("first_name"), payload.get("first_name"));
        Assert.assertEquals(response.jsonPath().getString("last_name"), payload.get("last_name"));
        Assert.assertEquals(response.jsonPath().getString("id"), payload.get("id"));
        Assert.assertEquals(response.jsonPath().getString("email"),payload.get("email"));
    }

    @Test
    public void registerUser() {
        User payload = UserPayload.createPayloadUsingPojo();
        Response response = userApiReader.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }
    @Test
    public void registerUserAndVerifyResponse() throws JsonProcessingException {
        //User payload = UserPayload.createPayloadUsingPojo();
        User payload = new User();
        Response response = userApiReader.createUser(payload);
        ObjectMapper objectMapper = new ObjectMapper();
        User getResponse = objectMapper.readValue(response.getBody().asString(), User.class);
        Assert.assertEquals(getResponse, payload);
    }

    @Test
    public void getUserList() {
        Response response = userApiReader.getUserApi();
        Assert.assertEquals(response.statusCode(), 200);

    }

}


