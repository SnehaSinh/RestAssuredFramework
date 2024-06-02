package pojos;

import Utils.RandomDataGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String email= RandomDataGenerator.getRandomAlphabet(5)+"@email.com";
    private String password=RandomDataGenerator.getRandomAlphabet(7);


}
