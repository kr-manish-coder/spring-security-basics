package com.manish.springsecuritybasics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security.
 */
@Configuration
public class MySecurityConfig {

    /**
     * Configures an in-memory user details service with a single user.
     *
     * @return the user details service
     */
    @Bean
    UserDetailsService userDetailsService() {
        // Create an in-memory user details manager
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        // Define a user with username "manish", password "manish" (encoded using BCrypt), and authority "read"
        UserDetails userDetail = User
                .withUsername("manish")
                .password(this.passwordEncoder().encode("manish"))
                .authorities("read")
                .build();

        // Add the user to the in-memory user details manager
        userDetailsService.createUser(userDetail);
        return userDetailsService;
    }

    /**
     * Configures a password encoder bean.
     *
     * @return the password encoder
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        // Return a BCrypt password encoder
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object
     * @return the security filter chain
     * @throws Exception if an error occurs
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Ensure that all requests are authenticated
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                // Use HTTP Basic authentication with default settings
                .httpBasic(Customizer.withDefaults());
        return http.build(); 
    }

}