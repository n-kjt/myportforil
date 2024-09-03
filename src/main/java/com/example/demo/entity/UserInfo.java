package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

/**
 * ユーザー情報 Entity
 * エンティティ：データベースのテーブル構造を表したオブジェクト
 */
@Data
@Table(name = "users")//クラス名とテーブル名が異なる場合のEntityクラスの宣言
public class UserInfo implements Serializable {

    /**
     * ID
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 名前
     */
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * メールアドレス
     */
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 自己紹介
     */
   @Column(value="self_introduction")
   
   public String selfIntroduction;

   public String getSelfIntroduction() {
       return selfIntroduction;
   }

   public void setSelfIntroduction(String selfIntroduction) {
       this.selfIntroduction = selfIntroduction;
   }

//    public String self_introduction;
//
//    public String getSelfIntroduction() {
//        return self_introduction;
//    }
//    public void setSelfIntroduction(String selfIntroduction) {
//        this.self_introduction = selfIntroduction;
//    }
    /**
     * パスワード
     */
    private String password;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 更新日時
     */
    private Date updateDate;

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * 登録日時
     */
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * 削除日時
     */
    private Date deleteDate;
    
    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

}
