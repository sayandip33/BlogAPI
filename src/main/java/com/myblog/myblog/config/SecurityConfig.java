package com.myblog.myblog.config;

import com.myblog.myblog.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_URLS={
            "/api/**",
            "/api/auth/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"
    };


    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }




}





















//for in memory authonethitaction


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails sayan = User.builder().username("sayan").password(passwordEncoder().
//                        encode("test")).roles("USER").build();
//
//        UserDetails admin = User.builder().username("ADMIN").password(passwordEncoder().
//                encode("admin")).roles("ADMIN").build();
//    return new InMemoryUserDetailsManager(sayan,admin);
//    }


// sir 1st ---->>>>>


//http.
//        csrf().
//        disable().
//        authorizeRequests().
//        antMatchers(HttpMethod.GET,"/api/**").permitAll().
//        anyRequest().
//        authenticated().
//        and().
//        httpBasic();




// for only admin
// but kaj korche naaa
//        http.
//                csrf().
//                disable().
//                authorizeRequests().
//                antMatchers(HttpMethod.GET,"/api/**").permitAll().
//                anyRequest().
//                hasRole("admin").
//                and().
//                httpBasic();

//        http.
//                csrf().
//                disable().
//                authorizeRequests().
//                antMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER").anyRequest().
//                authenticated().
//                and().
//                httpBasic();