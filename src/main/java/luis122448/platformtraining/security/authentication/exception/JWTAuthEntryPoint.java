package luis122448.platformtraining.security.authentication.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<Object>(-401, authException.getMessage(), authException.getCause().getMessage(), Optional.empty());

        // Convert apiResponseObject a JSON
        String jsonResponse = objectMapper.writeValueAsString(apiResponseObject);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonResponse);
    }
}
