package com.soft.srms.config;

import com.soft.srms.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.userDetailsService = customUserDetailsService;
        this.authenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/css/**", "/pictures/**", "/js/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/", "/login", "/registration").permitAll()
            .antMatchers("/dashboard/application/**").hasAnyRole("STUDENT","TENANT")
            .antMatchers("/dashboard/waiting").hasRole("STUDENT")
            .antMatchers("/dashboard/complaints/new").hasRole("TENANT")
            .antMatchers("/dashboard/admin","/dashboard/announcements/new","/dashboard/complaints/**","/dashboard/tenants").hasRole("ADMIN")
            .antMatchers("/dashboard/**").authenticated()
            .and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler)
            .and().logout().logoutSuccessUrl("/").permitAll()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .clearAuthentication(true)
            .and().csrf().ignoringAntMatchers("/h2-console/**")
            .and().rememberMe();

        // add this line to use H2 web console
        http.headers().frameOptions().disable();
    }
}
