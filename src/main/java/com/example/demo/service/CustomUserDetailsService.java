//ユーザーの情報を検索する役割をUserDetailsService が担っている。
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.dao.UserInfoMapper;
import com.example.demo.entity.UserInfo;

	@Service
	public class CustomUserDetailsService implements UserDetailsService{
		
		@Autowired //mapperをインスタンス化。DB接続をするMapperクラスを参照
		private UserInfoMapper userInfoMapper;
		
		//ログインを実装させる記述
		  @Override
		    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		        UserInfo userInfo = userInfoMapper.findByEmail(email);
		        if (userInfo == null) {
		            throw new UsernameNotFoundException("User not found");
		        }
		     // ログを追加して確認
//		        System.out.println("SelfIntroduction: " + userInfo.getSelfIntroduction());
		        return new CustomUserDetails(userInfo);
		    }
	}