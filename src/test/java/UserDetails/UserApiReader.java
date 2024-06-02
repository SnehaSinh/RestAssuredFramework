package UserDetails;

import RestUtil.RestUtility;
import io.restassured.response.Response;
import pojos.User;

import java.util.HashMap;
import java.util.Map;

public class UserApiReader {

    public Response createUser(Map<String, Object> createUserPayload) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint.create.user");
        return RestUtility.postMethodPayload(endpoint, createUserPayload, new HashMap<>());
    }

    public Response createUser(User createUserPayload) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint.create.user");
        return RestUtility.postMethodPayload(endpoint, createUserPayload, new HashMap<>());
    }
    public Response getUserApi() {
        String endpoint= (String) Base.dataFromJsonFile.get("endpoint.list.user");
        return RestUtility.getMethod(endpoint);
    }
}
