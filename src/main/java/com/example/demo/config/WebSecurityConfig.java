package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
	
@Configuration // クラスに@Configurationをつけることで、このクラスがSpringの設定クラスであることを示す

@EnableWebSecurity // @EnableWebSecurityをつけることで、Spring Securityのウェブセキュリティサポートを有効化する
public class WebSecurityConfig {

	@Autowired
	
    private UserDetailsService userDetailsService;
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/").permitAll()
	                .requestMatchers("user/login","/user/add","/user/top").permitAll()
	                .requestMatchers("/css/**").permitAll() 
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	            		.usernameParameter("email")
	            		.passwordParameter("password")
	            		// ログインを実行するページ
	            		.loginProcessingUrl("/user/login")
	            		// ログイン画面
	            		.loginPage("/user/login")
	            		// ログインに成功した場合の遷移先
	            		.defaultSuccessUrl("/user/top", true)
	            		// ログイン失敗時のURL
	            		.failureUrl("/user/login?error") // カスタム認証失敗ハンドラを設定
	            		)
	            
	            .logout((logout) -> logout
	                    // ログアウトした場合の遷移先
	                   .permitAll());
	            

	        return http.build();
	    }
	  
	
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @SuppressWarnings("deprecation")
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // UserDetailsServiceを設定し、ユーザー認証情報を提供
        auth.userDetailsService(userDetailsService)
                // パスワードエンコードを行わない設定
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    
}
