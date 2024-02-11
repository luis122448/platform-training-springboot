package luis122448.platformtraining.application.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static luis122448.platformtraining.application.web.constant.APIConstant.*;

@Slf4j
@RestController
@RequestMapping(API_TEST)
public class TestController {

    @Operation(tags = {TAG_TEST})
    @GetMapping("/hello-world")
    public ResponseEntity<String> helloWorldController() {
        return ResponseEntity.ok("Hello World");
    }

    @Operation(tags = {TAG_TEST})
    @GetMapping("/auth/credentials")
    public ResponseEntity<String> findByCredentials(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            String username = userDetailsCustom.getUsername();
            String company = userDetailsCustom.getCompany();
            return ResponseEntity.ok("Username: " + username + ", Company: " + company);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No valid authentication.");
        }
    }
}
