package Utils;

import net.datafaker.Faker;
import org.apache.commons.lang.RandomStringUtils;

public class RandomDataGenerator {
    public static Faker faker = new Faker();
    public static String getRandomData(DataTypesEnum dataTypes){
        switch (dataTypes){
            case First_Name:
                return faker.name().firstName();
            case Last_Name:
                return faker.name().lastName();
            case Full_Name:
                 return faker.name().fullName();
            case Address:
                return faker.address().fullAddress();
            default:
                return "DataType not available";
        }

    }
    public static String getRandomNumber(int count){
        return  faker.number().digits(count);
    }
    public static String getRandomAlphabet(int count){
        return RandomStringUtils.randomAlphabetic(count);
    }

}
