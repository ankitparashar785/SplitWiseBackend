package com.app.splitwise.config;

import com.app.splitwise.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
   private JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v3 (OpenAPI)
           "/users/register",
            "auth/login"

    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .cors(cors->cors.disable())
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder){
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = builder.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authenticationManager;
    }

}
