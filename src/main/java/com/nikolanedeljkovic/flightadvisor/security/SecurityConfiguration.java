package com.nikolanedeljkovic.flightadvisor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private UserDetailsService userDetails;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Primary
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/api/routes/shortest/**").hasAnyAuthority("ADMIN", "USER")
         .antMatchers("/api/routes/**").hasAuthority("ADMIN")
         .antMatchers("/api/cities").hasAuthority("ADMIN")
         .antMatchers("/api/cities/**").hasAnyAuthority("ADMIN", "USER")
         .antMatchers("/h2-console/**").permitAll();

    	 http.csrf().disable();
    	 http.headers().frameOptions().disable();
    	 http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }
}
