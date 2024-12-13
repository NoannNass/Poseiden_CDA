package com.nnk.springboot.security;

import com.nnk.springboot.service.AuthentificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthentificationService authentificationService;

    public ApplicationSecurityConfig(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
//        ;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(authentificationService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

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
    protected void configure (HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/","/css/*","/bidList/list").permitAll()
                .antMatchers( "/user/edit/**").hasAuthority("ROLE_ADMIN")
                .antMatchers( "/user/list").hasAuthority("ROLE_ADMIN")
                .antMatchers( "/user/add", "/user/delete/","/user/update/").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
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
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                ;

    }



}
