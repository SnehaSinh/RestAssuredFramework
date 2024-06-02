package UserDetails;

import Utils.jsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {
    public static Map<String,Object> dataFromJsonFile;
    static{
        String env=System.getProperty("env")==null?"qa":System.getProperty("env");
        try {
            dataFromJsonFile = jsonUtils.getJsonDataAsMap("/"+env+"/userNameAPI.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
