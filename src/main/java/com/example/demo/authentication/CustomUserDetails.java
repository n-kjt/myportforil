package com.example.demo.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserInfo;

public class CustomUserDetails implements UserDetails{

	
	private final UserInfo userInfo;

	
	public CustomUserDetails(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getUsername() {
		return this.userInfo.getName();
	}
	
	//@Overrideするとエラーになる為、オーバーライドを外しています
	public String getEmail() {
		return this.userInfo.getEmail();
	}
	
	@Override
	public String getPassword() {
		return this.userInfo.getPassword();
	}
	
	//@Override
	public String getSelf_introduction() {
		return this.userInfo.getSelf_introduction();
	}
	
	public Long getId(){
	return this.userInfo.getId();
}

}	
