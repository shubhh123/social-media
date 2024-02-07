package com.social.spring.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {
    /*
    * This method returns a SecurityFilterChain,
    * and when you call httpSecurity.build(),
    * it internally constructs an AuthenticationManager
    * with the provided configuration. */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //DEPICTS THAT THE INFO WILL NOT BE STORED ON THE SERVER SIDE.
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").authenticated() //ENDPOINT THAT BEGINS WITH api, must be authenticated.
                .anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class) //INVOKE THIS BEFORE INVOKING THE MAIN ENDPOINT URL

//                .httpBasic(Customizer.withDefaults()) //httpBasic() deprecated, so used Customizer.withDefaults()
                .csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    //encode password
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}