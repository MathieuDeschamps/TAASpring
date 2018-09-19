package istic.m2.taa.project.TAAProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
    
    protected void configure(HttpSecurity http) throws Exception {
        http	.csrf().ignoringAntMatchers("/**").and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/user/").permitAll()
                .anyRequest().permitAll()
                .anyRequest().authenticated()
                ;
              
    }
}
