package luis122448.platformtraining.security.authentication.auditing;

import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditingUsername implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            return Optional.of(userDetailsCustom.getIdUser());
        } else {
            return Optional.empty();
        }
    }
}
