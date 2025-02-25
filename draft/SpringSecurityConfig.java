package com.example.paris_janitor_api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*import com.example.paris_janitor_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;*/
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
*/
@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig {

   /* @Autowired
    private UserService userService;*/

    //@Autowired
    //private UserDetailsService userDetailsService;

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.csrf(
                httpSecurityCsrfConfigurer->
                        httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests((authorizationManagerRequestMatcherRegistry) ->
                            authorizationManagerRequestMatcherRegistry.requestMatchers("/api/auth/**","/api/bucket").permitAll()
                                    .anyRequest().authenticated()).build();

        /httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(auth -> {
            //auth.requestMatchers("/api/auth/admin").hasRole("ADMIN");
            auth.requestMatchers("/api/auth/signup","/api/auth/signin").permitAll();//.hasRole("USER");
            auth.anyRequest().authenticated();
        }).formLogin(Customizer.withDefaults()).build();/
    }*/

   /* @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();
        UserDetails admin = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/
    /*@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
/*
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }*/
}
