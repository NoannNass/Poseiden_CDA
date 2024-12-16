package com.nnk.springboot.security;

import com.nnk.springboot.service.AuthentificationService;
import com.nnk.springboot.security.JwtUtil;
import com.nnk.springboot.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthentificationService authentificationService;
    private final JwtFilter jwtFilter;

    public ApplicationSecurityConfig(AuthentificationService authentificationService, JwtFilter jwtFilter) {
        this.authentificationService = authentificationService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authentificationService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authentificationService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactive CSRF pour simplifier l'exemple
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/bidList/list", "/curvePoint/list").permitAll()
                .antMatchers("/register", "/login").permitAll()
                .antMatchers("/user/edit/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/list").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/add", "/user/delete/", "/user/update/").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Insère le filtre JWT avant le filtre de Spring Security
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}