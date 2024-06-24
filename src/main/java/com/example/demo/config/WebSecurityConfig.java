package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Autowired
	
    private UserDetailsService userDetailsService;
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/").permitAll()
	                .requestMatchers("/user/add","/user/top").permitAll()
	                .requestMatchers("/css/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	            	.usernameParameter("email")
	            	.passwordParameter("password")
	            	// ログインを実行するページ
	                .loginProcessingUrl("/user/add")
	            	// ログイン画面
	                .loginPage("/user/add")
	                // ログインに成功した場合の遷移先
	                .defaultSuccessUrl("/user/top", true)
	                
	            );
	            

	        return http.build();
	    }
	
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
    
    
}

