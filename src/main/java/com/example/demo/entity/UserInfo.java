package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

/**
 * ユーザー情報 Entity
 * エンティティ：データベースのテーブル構造を表したオブジェクト
 */

@Table(name = "users")
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

    // テーブルから取得した値を格納するentityを作成
    public record UserAccount(int accountId, String password, String selfIntroduction) {}

    
    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSelfIntroduction() {
        return self_introduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.self_introduction = selfIntroduction;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}
