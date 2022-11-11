package com.vn.security;

import com.vn.utils.Para;
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


    /**
     * Processing log in, log out, authorize for user who has an account in the system what exactly URL they can access
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/signin?action=false")
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(2*12*60*60)
                .key("abc")
                .rememberMeCookieName("remember-me")
                .and()
                .logout()
                .logoutUrl("/home_logout")
                .deleteCookies("remember-me", "JSESSIONID")
                .clearAuthentication(true)
                .and()
                .authorizeHttpRequests()
                .antMatchers(Para.ignoreSecurityPages)
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/home/").hasRole("CUSTOMER")
                .antMatchers("/home/").hasRole("OWNER")
                .antMatchers("/forgot_password", "/reset_password","/customer_profile", "/customer_booking", "/customer_wallet", "/logout", "/about").hasRole("CUSTOMER")
                .antMatchers("/forgot_password", "/reset_password", "/add_car", "/owner_profile", "/owner_cars", "/owner_wallet", "/owner_reports", "/logout", "/about").hasRole("OWNER")
                .anyRequest()
                .authenticated();
    }


    /**
     * Authenticate for user log in with their email and encrypt password, then check encrypt password at the form log in with encrypt password in the DB
     * If password is match, redirect user to home page
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailsService);

        auth.authenticationProvider(provider);

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
