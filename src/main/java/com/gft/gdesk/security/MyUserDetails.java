package com.gft.gdesk.security;

import com.gft.gdesk.util.UserModelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Data
public class MyUserDetails implements UserDetails {

    private long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String status;

    public MyUserDetails(long id, String email, String password, Collection<? extends GrantedAuthority> authorities, String status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.status = status;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
