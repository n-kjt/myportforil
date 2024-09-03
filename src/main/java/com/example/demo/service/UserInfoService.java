//サービスクラス：リッポジトリクラス(DBから情報をとってくるクラス)を呼び出すクラス
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;

/**
 * ユーザー情報 Service
 */
@Service
public class UserInfoService {

    /**
     * ユーザー情報 Mapper
     */
    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
	PasswordEncoder passwordEncoder;

    /**
     * ユーザ情報登録
     * @param userAddRequest リクエストデータ
     */
    public void save(UserAddRequest userAddRequest) {
    	// userAddRequestオブジェクトからパスワードを取得、passwordEncoderで暗号化の処理を行う
        String encodedPassword = passwordEncoder.encode(userAddRequest.getPassword());
        // 暗号化されたパスワードをuserAddRequestリクエストオブジェクトに設定する
		userAddRequest.setPassword(encodedPassword);
		// 暗号化されたパスワードを含むユーザー情報をデータベースに保存する
		userInfoMapper.save(userAddRequest);
    }

    /**
     * ユーザ情報更新
     * @param userEditRequest リクエストデータ
     */
    public void update(UserUpdateRequest userUpdateRequest) {
        userInfoMapper.update(userUpdateRequest);
    }

    public List<UserInfo> findAll() {
    	return userInfoMapper.findAll();
    }
    
    // ユーザーIDで検索
    public UserInfo findById(Long id) {
        return userInfoMapper.findById(id);
    }
    
//    public UserInfo findByEmail(String email) {
//        return userInfoMapper.findByEmail(email);
//    }
//    

    
    
}