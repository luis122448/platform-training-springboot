package luis122448.platformtraining.security.authentication.provider;

import luis122448.platformtraining.security.authentication.component.CustomWebAuthenticationDetails;
import luis122448.platformtraining.security.authentication.component.TOTPUtil;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.*;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
    public final TOTPUtil totpUtil;
    public CustomDaoAuthenticationProvider(TOTPUtil totpUtil) {
        this.totpUtil = totpUtil;
    }

    public Authentication authentication(Authentication authentication) throws SecurityException{
//        String verifyCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerifyCode();
        if (authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            String secretCode = userDetailsCustom.getSecretCode();
            if (userDetailsCustom.getNivel() == 1 && secretCode != null){
                if (!totpUtil.verifyCode(secretCode)) {
                    throw new SecurityException(String.format(INVALID_VERIFY_CODE,secretCode));
                }
            }
            return new UsernamePasswordAuthenticationToken(
                    userDetailsCustom, userDetailsCustom.getPassword(), userDetailsCustom.getAuthorities()
            );
        } else {
            throw new SecurityException("NO DEFINIDO");
        }
    }

}
