package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Data
public class UserInfo implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名前
     */
    private String name;

    /**
     * メールアドレス
     */
    private String email;
    
    /**
     * 自己紹介
     */
    private String self_introduction;

    /**
     * パスワード
     */
    private String password;

    /**
     * 更新日時
     */
    private Date updateDate;

    /**
     * 登録日時
     */
    private Date createDate;

    /**
     * 削除日時
     */
    private Date deleteDate;
}