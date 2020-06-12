package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import services.MyUserDetailsService;

/**
 * Created by ch on 2020-05-06
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/about", "/registration").permitAll()
                .antMatchers("/homepage", "/homepage.html", "/reservation", "reservation.html").access("hasAnyAuthority('ROLE_USER','ROLE_ADMIN', 'ROLE_MANAGER','ROLE_COOK','ROLE_WAITER')")
                .antMatchers("/admin/menu", "/admin/menu/**").access("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER','ROLE_COOK')")
                .antMatchers("/admin/report", "/admin/report/**").access("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
                .antMatchers("/admin/reservation", "/admin/reservation/**","/admin").access("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER','ROLE_COOK','ROLE_WAITER')")
                .antMatchers("/admin/table", "/admin/table/**").access("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
                .antMatchers("/admin/worker", "/admin/worker/**").access("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/homepage.html", false)
                .permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**", "/login", "/logout")//don't apply CSRF protection to /h2-console
                .and().headers().frameOptions().sameOrigin()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        System.out.println("dasdadsadad");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails user = User.withUsername("d@op.pl")
                .passwordEncoder(encoder::encode)
                .password("d")
                .roles("ADMIN")
                .build();
        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager(user);
        auth
                .userDetailsService(uds)
                .passwordEncoder(encoder);
        //auth.userDetailsService(userDetailsService);
    }


    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }
}
