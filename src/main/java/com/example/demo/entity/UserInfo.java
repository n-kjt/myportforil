package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */

@Table(name = "users")
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

    //テーブルから取得した値を格納するentityを作成 
    public record UserAccount(int accountId,String password,String self_introduction) {};

}