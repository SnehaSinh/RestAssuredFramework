package UserDetails;

import Utils.DataTypesEnum;
import Utils.RandomDataGenerator;
import net.datafaker.Faker;
import org.apache.commons.lang.RandomStringUtils;
import pojos.User;

import java.util.HashMap;
import java.util.Map;

public class UserPayload {
    public static Map<String, Object> createPayloadUsingMap() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", RandomDataGenerator.getRandomNumber(5));
        requestBody.put("email", RandomDataGenerator.getRandomAlphabet(6) + "@gmail.com");
        requestBody.put("first_name", RandomDataGenerator.getRandomData(DataTypesEnum.First_Name));
        requestBody.put("last_name", RandomDataGenerator.getRandomData(DataTypesEnum.Last_Name));
        return requestBody;
    }

    public static User createPayloadUsingPojo() {
        return User
                .builder()
                .email(RandomDataGenerator.getRandomAlphabet(6) + "@gmail.com")
                .password(RandomDataGenerator.getRandomData(DataTypesEnum.Last_Name)).build();


    }
}
