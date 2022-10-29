package com.vn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .formLogin()
                .loginPage("/signing")
                .loginProcessingUrl("/signing")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/signing?action=false")
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(2*12*60*60)
                .key("abc")
                .rememberMeCookieName("remember-me")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me", "JSESSIONID")
                .clearAuthentication(true)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/forgot-pass-step1", "/forgot-pass-step2", "/signup", "/signing", "/logout", "/assets/css/**", "/assets/js/**", "/assets/icon/**", "/assets/boostrap/css/**", "/assets/boostrap/js/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/home/").hasRole("CUSTOMER")
                .antMatchers("/home/").hasRole("OWNER")
                .antMatchers("/customer_profile", "/customer_booking", "/customer_wallet", "/logout", "/about").hasRole("CUSTOMER")
                .antMatchers("/add_car", "/owner_profile", "/owner_cars", "/owner_wallet", "/owner_reports", "/logout", "/about").hasRole("OWNER")
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailsService);

        auth.authenticationProvider(provider);

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
