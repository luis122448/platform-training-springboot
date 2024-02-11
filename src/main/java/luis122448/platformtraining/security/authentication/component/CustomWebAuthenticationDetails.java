package luis122448.platformtraining.security.authentication.component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    @Getter
    private String verifyCode;
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verifyCode =  request.getParameter("verifyCode");
    }

    public CustomWebAuthenticationDetails(String remoteAddress, String sessionId) {
        super(remoteAddress, sessionId);
    }
}
