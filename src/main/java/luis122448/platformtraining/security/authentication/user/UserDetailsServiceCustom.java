package luis122448.platformtraining.security.authentication.user;


import luis122448.platformtraining.security.application.domain.model.UserModel;
import luis122448.platformtraining.security.application.domain.service.AuthService;
import luis122448.platformtraining.security.application.domain.service.LoginService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private final LoginService loginService;
    public UserDetailsServiceCustom(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetailsCustom loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserModel userModel = this.loginService.login("",username);
            return UserDetailsCustom.builder()
                    .idCompany(userModel.getIdCompany())
                    .idUser(userModel.getIdUser())
                    .company(userModel.getCompany())
                    .username(userModel.getUsername())
                    .encode(userModel.getEncode())
                    .nivel(userModel.getNivel())
                    .secretCode(userModel.getSecretCode())
                    .authorities(getAuthorities(userModel.getRole()))
                    .registdate(userModel.getRegistdate())
                    .expiredate(userModel.getExpiredate())
                    .status(userModel.getStatus())
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException("ERROR UNKNOWN", e);
        }
    }

    public UserDetailsCustom loadUserByUsernameAndCompany(String company, String username) throws UsernameNotFoundException {
        try{
            UserModel userModel = this.loginService.login(company,username);
            return UserDetailsCustom.builder()
                    .idCompany(userModel.getIdCompany())
                    .idUser(userModel.getIdUser())
                    .company(userModel.getCompany())
                    .username(userModel.getUsername())
                    .encode(userModel.getEncode())
                    .nivel(userModel.getNivel())
                    .secretCode(userModel.getSecretCode())
                    .authorities(getAuthorities(userModel.getRole()))
                    .registdate(userModel.getRegistdate())
                    .expiredate(userModel.getExpiredate())
                    .status(userModel.getStatus())
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException("ERROR IN LOAD BY USERNAME", e);
        }
    }

    private List<GrantedAuthority> getAuthorities(String role){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + "TEST"));
        return authorityList;
    }
}
