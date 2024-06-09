package luis122448.platformtraining.security.application.web.controller;

import luis122448.platformtraining.security.application.domain.model.LoginModel;
import luis122448.platformtraining.security.application.domain.model.RefreshTokenModel;
import luis122448.platformtraining.security.application.domain.model.VerifyCodeModel;
import luis122448.platformtraining.security.application.domain.service.AuthService;
import luis122448.platformtraining.util.exception.GenericAuthServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseAuth;
import luis122448.platformtraining.util.object.api.ApiResponseMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.API_AUTH;

@RestController
@RequestMapping(API_AUTH)
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) throws GenericAuthServiceException {
        ApiResponseMetadata<?,?> response = this.authService.login(loginModel);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException {
        ApiResponseMetadata<?,?> response = this.authService.verifyCode(verifyCodeModel);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException{
        ApiResponseMetadata<?,?> response = this.authService.refreshToken(refreshTokenModel);
        return ResponseEntity.ok(response);
    }

}
