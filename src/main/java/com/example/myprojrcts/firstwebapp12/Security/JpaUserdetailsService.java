package com.example.myprojrcts.firstwebapp12.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JpaUserdetailsService implements UserDetailsService
{

    private final UserRepository userRepository;
    public JpaUserdetailsService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository
                .findByUserName(username)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException(" not found"));
    }
}
