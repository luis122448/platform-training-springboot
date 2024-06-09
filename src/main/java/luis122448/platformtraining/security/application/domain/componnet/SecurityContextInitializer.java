package luis122448.platformtraining.security.application.domain.componnet;

import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextInitializer {

    public Long getIdCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return userDetailsCustom.getIdCompany();
        } else {
            return 0L;
        }
    }

    public Long getIdUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return userDetailsCustom.getIdUser();
        } else {
            return 0L;
        }
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return userDetailsCustom.getUsername();
        } else {
            return "unknown";
        }
    }
}
