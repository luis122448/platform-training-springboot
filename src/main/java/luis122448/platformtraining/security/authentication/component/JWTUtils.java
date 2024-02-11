package luis122448.platformtraining.security.authentication.component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.security.authentication.user.UserDetailsServiceCustom;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static luis122448.platformtraining.security.authentication.constant.AUTHConstant.*;

@Slf4j
@Component
public class JWTUtils {

    private final UserDetailsServiceCustom userDetailsServiceCustom;

    public JWTUtils(UserDetailsServiceCustom userDetailsServiceCustom) {
        this.userDetailsServiceCustom = userDetailsServiceCustom;
    }

    public  String createJwtToken(String company, String username, Boolean isRefreshToken ) throws SecurityException{
        try{
            UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(company, username);

            long time = (!isRefreshToken) ? TOKEN_EXPIRATION_TIME_TOKEN : TOKEN_EXPIRATION_TIME_REFRESH_TOKEN;

            Map<String, Object> tokenData  = new HashMap<>();
            tokenData.put(AUTHORITIES, userDetailsCustom.getAuthorities());
            tokenData.put(ID_COMPANY, userDetailsCustom.getIdCompany());
            tokenData.put(ID_USER, userDetailsCustom.getIdUser());
            tokenData.put(COMPANY, userDetailsCustom.getCompany());
            tokenData.put(USERNAME, userDetailsCustom.getUsername());

            return Jwts
                    .builder()
                    .setSubject(userDetailsCustom.getUsername())
                    .setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
                    .setExpiration(new Date(System.currentTimeMillis() + time))
//                    .signWith(SUPER_SECRET_KEY)
                    .signWith(SignatureAlgorithm.HS512,SUPER_SECRET_KEY)
                    .addClaims(tokenData)
                    .compact();
        } catch (SecurityException e) {
            log.info("Error {}", e.getMessage());
            throw new SecurityException("ERROR IN GENERATE TOKEN", e);
        }
    }

    public Map<String, Object> getDataJwtToken(String token) throws SecurityException {
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(SUPER_SECRET_KEY).build().parseClaimsJws(token).getBody();
            return new HashMap<>(claims);
        } catch (SignatureException e) {
            throw new SecurityException("INVALID LOGIN, PLEASE RE-LOGIN", e);
        }
    }

    public boolean validateJwtToken(String authToken) throws SecurityException {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SUPER_SECRET_KEY).build();
            return true;
        } catch (ExpiredJwtException e) {
            throw new SecurityException("TOKEN EXPIRED", e);
        } catch (UnsupportedJwtException e) {
            throw new SecurityException("UNSUPPORTED TOKEN", e);
        } catch (SignatureException e) {
            throw new SecurityException("TOKEN INVALID, NEED RE-LOGIN",e);
        } catch (JwtException e) {
            throw new SecurityException("INVALID TOKEN", e);
        }
    }

    public String createJwtTokenRefresh(String refreshToken) throws SecurityException {
        try{
            Map<String, Object> tokenData = this.getDataJwtToken(refreshToken);
            String username = tokenData.get(USERNAME).toString();
            return Jwts
                    .builder()
                    .setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_REFRESH_TOKEN))
                    .addClaims(tokenData)
//                    .signWith(SUPER_SECRET_KEY)
                    .signWith(SignatureAlgorithm.HS512,SUPER_SECRET_KEY)
                    .compact();
        } catch (SecurityException e) {
            throw new SecurityException("ERROR IN GENERATE REFRESH TOKEN", e);
        }
    }

    public String parseJwt(HttpServletRequest httpServletRequest) throws SecurityException {
        String headerAuth = httpServletRequest.getHeader(HEADER_AUTHORIZATION_KEY);
        log.info("Header Auth {}",headerAuth);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(TOKEN_BEARER_PREFIX)) {
            log.info("Token {}",headerAuth.substring(7));
            return headerAuth.substring(7);
        }
        return null;
    }
}
