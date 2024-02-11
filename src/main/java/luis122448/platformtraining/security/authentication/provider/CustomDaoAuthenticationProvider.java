package luis122448.platformtraining.security.authentication.provider;

import luis122448.platformtraining.security.authentication.component.CustomWebAuthenticationDetails;
import luis122448.platformtraining.security.authentication.component.TOTPUtils;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.*;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    public final TOTPUtils totpUtils;

    public CustomDaoAuthenticationProvider(TOTPUtils totpUtils) {
        this.totpUtils = totpUtils;
    }

    public Authentication authentication(Authentication authentication) throws SecurityException{
        String verifyCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerifyCode();
        if (authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            String secretCode = userDetailsCustom.getSecretCode();
            if (userDetailsCustom.isVerifyCode()) {
                if (!totpUtils.verifyCode(secretCode,verifyCode)) {
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
