package luis122448.platformtraining.security.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.authentication.component.JWTUtils;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.security.authentication.user.UserDetailsServiceCustom;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.*;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, SecurityException {
        try {

            String jwt = this.jwtUtils.parseJwt(request);

            if (jwt !=  null && this.jwtUtils.validateJwtToken(jwt)){
                log.info("Token {}", jwt);
                Map<String, Object> tokenData = this.jwtUtils.getDataJwtToken(jwt);
                String company = tokenData.get(COMPANY).toString();
                String username = tokenData.get(USERNAME).toString();

                UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(company, username);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetailsCustom, userDetailsCustom.getPassword(), userDetailsCustom.getAuthorities()
                );

                String token = this.jwtUtils.createJwtToken(company, username, false);

                response.addHeader("Access-Control-Expose-Headers", "Authorization");
                response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                log.info("No Token");
            }
        } catch (SecurityException e){
            handleAuthenticationException(response, e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private void handleAuthenticationException(HttpServletResponse response, SecurityException e) throws IOException {
        log.info("handleAuthenticationException {}", e.getMessage());
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<Object>(-401, e.getMessage(), e.getCause().getMessage(), Optional.empty());

        // Convert apiResponseObject a JSON
        String jsonResponse = objectMapper.writeValueAsString(apiResponseObject);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonResponse);
    }

}
