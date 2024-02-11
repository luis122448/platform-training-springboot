package luis122448.platformtraining.security.authentication.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsCustom implements UserDetails {

    @Getter
    private Long idCompany;
    @Getter
    private Long idUser;
    @Getter
    private String company;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private boolean isVerifyCode;
    @Getter
    private String secretCode;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDate expirationUsername;
    private LocalDate expirationPassword;
    private String status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return LocalDate.now().isBefore(this.expirationUsername);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status.equals("S") || this.status.equals("Y");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return LocalDate.now().isBefore(this.expirationPassword);
    }

    @Override
    public boolean isEnabled() {
        return this.status.equals("S") || this.status.equals("Y");
    }
}
