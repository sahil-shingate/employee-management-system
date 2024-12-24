package com.ems.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/home","/register","/saveUser").permitAll()
                .requestMatchers("/welcome").authenticated()
                .requestMatchers("/admin").hasAuthority("Admin")
                .requestMatchers("/mgr").hasAuthority("Manager")
                .requestMatchers("/emp").hasAuthority("Employee")
                .requestMatchers("/hr").hasAuthority("HR")
                .requestMatchers("/common").hasAnyAuthority("Employeee,Manager,Admin")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/welcome",true)

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")

                .and()
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

}
