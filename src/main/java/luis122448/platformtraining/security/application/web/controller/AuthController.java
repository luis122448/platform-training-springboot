package luis122448.platformtraining.security.application.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.application.domain.model.AuthModel;
import luis122448.platformtraining.security.application.domain.model.RefreshTokenModel;
import luis122448.platformtraining.security.application.domain.model.TokenModel;
import luis122448.platformtraining.security.application.domain.model.VerifyCodeModel;
import luis122448.platformtraining.security.authentication.component.JWTUtils;
import luis122448.platformtraining.security.authentication.component.TOTPUtils;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.security.authentication.user.UserDetailsServiceCustom;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.*;

@Slf4j
@RestController
@RequestMapping(API_AUTH)
@AllArgsConstructor
public class AuthController {

    private JWTUtils jwtUtils;
    private TOTPUtils totpUtils;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceCustom userDetailsServiceCustom;

    @Operation(tags = {TAG_AUTH})
    @PostMapping(PATH_LOGIN)
    public ResponseEntity<?> login(@RequestBody AuthModel authModel){
        UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(authModel.getCompany(),authModel.getUsername());
        if (!this.bCryptPasswordEncoder.matches(authModel.getPassword(), userDetailsCustom.getPassword())) {
            return ResponseEntity.ok(new ApiResponseObject<TokenModel>(INVALID_CREDENTIALS));
        }
        if(userDetailsCustom.isVerifyCode()){
            String verifyCode = totpUtils.generateCode(userDetailsCustom.getSecretCode());
            VerifyCodeModel verifyCodeModel = new VerifyCodeModel(verifyCode);
            return ResponseEntity.ok(new ApiResponseObject<VerifyCodeModel>(0,ALERT_VERIFY_CODE,Optional.of(verifyCodeModel)));
        }
        String token = this.jwtUtils.createJwtToken(userDetailsCustom.getCompany(),userDetailsCustom.getUsername(),false);
        String refreshToken = this.jwtUtils.createJwtToken(userDetailsCustom.getCompany(),userDetailsCustom.getUsername(),true);
        return ResponseEntity.ok(new ApiResponseObject<TokenModel>(Optional.of(new TokenModel(token,refreshToken))));
    }

    @Operation(tags = {TAG_AUTH})
    @PostMapping(PATH_VERIFY_CODE)
    public ResponseEntity<?> verifyCode(@RequestBody AuthModel authModel){
        UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(authModel.getCompany(),authModel.getUsername());
        if(!this.totpUtils.verifyCode(userDetailsCustom.getSecretCode(), authModel.getVerifyCode())){
            return ResponseEntity.ok(new ApiResponseObject<TokenModel>(String.format(INVALID_VERIFY_CODE,authModel.getVerifyCode())));
        }
        String token = this.jwtUtils.createJwtToken(userDetailsCustom.getCompany(),userDetailsCustom.getUsername(),false);
        String refreshToken = this.jwtUtils.createJwtToken(userDetailsCustom.getCompany(),userDetailsCustom.getUsername(),true);
        return ResponseEntity.ok(new ApiResponseObject<TokenModel>(Optional.of(new TokenModel(token,refreshToken))));
    }

    @Operation(tags = {TAG_AUTH})
    @PostMapping(PATH_REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenModel refreshTokenModel){
        String refreshToken = refreshTokenModel.getRefreshToken();
        if (!this.jwtUtils.validateJwtToken(refreshToken)){
            return ResponseEntity.ok(new ApiResponseObject<TokenModel>(INVALID_TOKEN));
        }
        String token = this.jwtUtils.createJwtTokenRefresh(refreshToken);
        Map<String, Object> tokenData = this.jwtUtils.getDataJwtToken(refreshToken);
        String company = tokenData.get(COMPANY).toString();
        String username = tokenData.get(USERNAME).toString();
        UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(company, username);
        return ResponseEntity.ok(new ApiResponseObject<TokenModel>(Optional.of(new TokenModel(token,refreshToken))));
    }
}
