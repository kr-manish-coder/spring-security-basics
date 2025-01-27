package com.manish.springsecuritybasics.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
// Commented out the userDetailsService() method because we are using AuthenticationProvider instead of UserDetailsService here: {@link com.manish.springsecuritybasics.security.MyAuthenticationProvider}
//    @Bean
//    UserDetailsService userDetailsService() {
//        // Create an in-memory user details manager
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//
//        // Define a user with username password (encoded using BCrypt), and authority "read"
//        UserDetails userDetail = User
//                .withUsername("manish")
//                .password(this.passwordEncoder().encode("manish"))
//                .authorities("read")
//                .build();
//
//        // Add the user to the in-memory user details manager
//        userDetailsService.createUser(userDetail);
//        return userDetailsService;
//    }

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
                       //  authorizeRequests.anyRequest().authenticated() // This will allow all authenticated users to access all the endpoints
                        authorizeRequests
                                .requestMatchers("/hello").authenticated().anyRequest().denyAll() // This will allow only authenticated users to access the /hello endpoint
                )
                // Use HTTP Basic authentication with default settings
                .httpBasic(Customizer.withDefaults());

        // Add a custom filter before the BasicAuthenticationFilter
        http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }

}