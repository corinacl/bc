package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import api.Uris;
import entities.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {  
        http.csrf().disable().authorizeRequests()//
        .antMatchers(HttpMethod.GET, Uris.SERVLET_MAP + Uris.ADMINS).permitAll()//
        .antMatchers(Uris.SERVLET_MAP + Uris.TOKENS + "/**").permitAll()//
        .antMatchers(Uris.SERVLET_MAP + Uris.ADMINS + "/**").hasRole(Role.ADMIN.name())//
        .antMatchers(Uris.SERVLET_MAP + Uris.PLANNING + "/**").authenticated()//
        .antMatchers(HttpMethod.GET, Uris.SERVLET_MAP + Uris.BUNGALOWS + "/**").authenticated()//
        .antMatchers(HttpMethod.GET, Uris.SERVLET_MAP + Uris.TYPE + "/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())//
        .antMatchers(HttpMethod.POST, Uris.SERVLET_MAP + Uris.BUNGALOWS + Uris.SEARCH + "/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())//
        .antMatchers(Uris.SERVLET_MAP + Uris.BUNGALOWS + "/**").hasRole(Role.ADMIN.name())//
        .antMatchers(Uris.SERVLET_MAP + Uris.TYPE + "/**").hasRole(Role.ADMIN.name())//
        .antMatchers(Uris.SERVLET_MAP + Uris.USERS + "/**").hasRole(Role.ADMIN.name())//
        .antMatchers(Uris.SERVLET_MAP + Uris.BOOKINGS + "/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())//
        .antMatchers(Uris.SERVLET_MAP + Uris.CLIENTS + "/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())//
       .and().httpBasic();// 
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
