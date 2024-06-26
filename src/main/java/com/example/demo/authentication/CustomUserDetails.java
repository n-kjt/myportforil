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
	
	//ログイン認証のために追加したけどなにこれ??
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
	//ログイン認証のために追加したけどたぶん不要

}	
