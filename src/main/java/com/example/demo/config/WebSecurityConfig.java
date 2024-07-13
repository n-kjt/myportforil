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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
	
@SuppressWarnings("deprecation")
@Configuration // クラスに@Configurationをつけることで、このクラスがSpringの設定クラスであることを示す

@EnableWebSecurity // @EnableWebSecurityをつけることで、Spring Securityのウェブセキュリティサポートを有効化する
public class WebSecurityConfig {

	@Autowired
	
    private UserDetailsService userDetailsService;
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())//CSRF保護を無効にするための設定
	            .authorizeHttpRequests(authorize -> authorize
	            	.requestMatchers("/").authenticated()
		            .requestMatchers("/user/login","/user/add","/user/profileedit","/user/category","/user/top").permitAll()//7:.permitAll()は認証なくても表示を許可
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
	            		.failureUrl("/user/login?error")
	            		.permitAll()
	            		)
	            //7:ログアウト機能
	            .logout(logout -> logout
	                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                    .logoutUrl("/logout")
	                    .logoutSuccessUrl("/login")
	                    .invalidateHttpSession(true)
	                    .deleteCookies("JSESSIONID")
	                    .permitAll()
	                    
	                );
	        
	        return http.build();
	    }
	  
//5:PWのハッシュ化
  @Bean
  public PasswordEncoder passwordEncoder() {	
      return new BCryptPasswordEncoder();
  }
  
  //6:ログイン認証
  @SuppressWarnings("deprecation")
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // UserDetailsServiceを設定し、ユーザー認証情報を提供
        auth.userDetailsService(userDetailsService)
                // パスワードエンコードを行わない設定
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
  
    
}
