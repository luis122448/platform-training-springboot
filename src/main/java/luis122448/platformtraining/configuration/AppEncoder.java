package luis122448.platformtraining.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppEncoder {

    public static void main(String[] arg){
        System.out.println(new BCryptPasswordEncoder().encode("10737418243486784401"));
    }
}
