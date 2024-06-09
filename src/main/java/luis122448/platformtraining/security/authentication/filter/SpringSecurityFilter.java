package luis122448.platformtraining.security.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luis122448.platformtraining.security.authentication.component.CustomWebAuthenticationDetailsSource;
import luis122448.platformtraining.security.authentication.component.TOTPUtil;
import luis122448.platformtraining.security.authentication.exception.JWTAuthEntryPoint;
import luis122448.platformtraining.security.authentication.provider.CustomDaoAuthenticationProvider;
import luis122448.platformtraining.security.authentication.user.UserDetailsServiceCustom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityFilter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TOTPUtil totpUtil;
    private final UserDetailsServiceCustom userDetailsServiceCustom;
    private final ObjectMapper objectMapper;
    private final CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws SecurityException{
        try{
            log.info("securityFilterChain");
            httpSecurity.csrf(AbstractHttpConfigurer::disable);
            httpSecurity
                    .authorizeHttpRequests(authorize ->
                            authorize
                                    .requestMatchers("**").permitAll()
                                    .requestMatchers("/auth/**").permitAll()
                    );
            httpSecurity
                    .authorizeHttpRequests(authorize ->
                            authorize
                                    .requestMatchers("/platform-training/**").hasAnyRole("ADMIN","TEST","MASTER","USER","PUBLIC")
                                    .anyRequest()
                                    .authenticated())
                    .sessionManagement(sessionManagement ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .exceptionHandling(exceptionHandling ->
                            exceptionHandling.authenticationEntryPoint(new JWTAuthEntryPoint(objectMapper)));
            httpSecurity.authenticationProvider(authenticationProvider());
            httpSecurity.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();

        } catch (Exception e){
            throw new SecurityException(e);
        }
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter(){
        return new JWTAuthorizationFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        CustomDaoAuthenticationProvider customDaoAuthenticationProvider = new CustomDaoAuthenticationProvider(totpUtil);
        customDaoAuthenticationProvider.setUserDetailsService(this.userDetailsServiceCustom);
        customDaoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
        return customDaoAuthenticationProvider;
    }

}
