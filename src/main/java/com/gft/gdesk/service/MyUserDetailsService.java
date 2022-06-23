package com.gft.gdesk.service;

import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.repository.UserModelRepository;
import com.gft.gdesk.security.MyUserDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userModelRepository.findUserModelByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userModel.getROLE()));
        return new MyUserDetails(userModel.getId(), userModel.getEmail(), userModel.getPassword(), authorities, userModel.getStatus());
    }
}
