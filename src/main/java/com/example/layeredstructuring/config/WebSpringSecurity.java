package com.example.layeredstructuring.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {

    private UserDetailsService userDetailsService;

    public WebSpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
          .authorizeRequests(authorize ->
            authorize.requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()
              .requestMatchers(new AntPathRequestMatcher("/signup/**")).permitAll()
              .requestMatchers(new AntPathRequestMatcher("/admin/**"))
              .hasAnyAuthority("ADMIN", "BLOGGER")
              .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
              .requestMatchers(new AntPathRequestMatcher("/posts/**")).permitAll()
              .requestMatchers(new AntPathRequestMatcher("/{post_id}/comments")).permitAll()
              .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
              .anyRequest().authenticated()
          )
          .formLogin( form -> form
            .loginPage("/signin")
            .defaultSuccessUrl("/admin/posts")
            .loginProcessingUrl("/signin")
            .permitAll()
          ).logout( logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
          .permitAll()
        );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder());
    }
}