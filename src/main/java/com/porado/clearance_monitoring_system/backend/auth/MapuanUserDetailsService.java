package com.porado.clearance_monitoring_system.backend.auth;

import com.porado.clearance_monitoring_system.backend.model.User;
import com.porado.clearance_monitoring_system.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapuanUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username.toLowerCase())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username)
                );

        return new MapuanUserDetails(user);
    }

}
