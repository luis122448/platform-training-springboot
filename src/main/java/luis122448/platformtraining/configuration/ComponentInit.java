package luis122448.platformtraining.configuration;

import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.components.RegisterUserInit;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ComponentInit implements ApplicationRunner {

    private final RegisterUserInit registerUserInit;

    public ComponentInit(RegisterUserInit registerUserInit) {
        this.registerUserInit = registerUserInit;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Go ComponentInit");
        this.registerUserInit.RegisterUserByModule(1L,0L);
        this.registerUserInit.RegisterUserByModule(1L,1L);
    }
}
