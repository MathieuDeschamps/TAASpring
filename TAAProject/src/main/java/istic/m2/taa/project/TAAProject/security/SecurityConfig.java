package istic.m2.taa.project.TAAProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
        
    }
    
    protected void configure(HttpSecurity http) throws Exception {
    	
  
        http	.csrf().disable()
        		.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                //.antMatchers(HttpMethod.PUT,"/**").authenticated()
                //.antMatchers(HttpMethod.PUT,"/user/").authenticated()
                //.anyRequest().permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                ;
              
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
