package com.manish.springsecuritybasics.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Custom authentication provider for Spring Security.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    /**
     * Authenticates the user based on the provided authentication object.
     *
     * @param authentication the authentication request object
     * @return a fully authenticated object including credentials
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the username and password from the authentication object
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Check if the username and password are valid
        if ("manish".equals(username) && "manish".equals(password)) {
            // Create a new authentication object with the username, password, and authorities
            return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("read")));
        } else {
            // Throw an authentication exception if the username and password are invalid
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    /**
     * Checks if this authentication provider supports the given authentication type.
     *
     * @param authentication the authentication type
     * @return true if the authentication type is supported, false otherwise
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // Return true if the authentication object is of type UsernamePasswordAuthenticationToken
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}