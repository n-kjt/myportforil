package com.example.demo.dto;

import java.io.Serializable;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報更新リクエストデータ
 *
 */

@Data//エンティティ：内のゲッターとセッター定義を省略できる
@EqualsAndHashCode(callSuper = false)//継承があるオブジェクトで@Data を使うと警告が入るので回避のために
public class UserUpdateRequest implements Serializable {

    /**
     * ユーザーID
     */
	@Column(value = "id")
    @NotNull
    private Long id;
    
    /**
    * 自己紹介文
    */
   @Column(value = "self_introduction")
   @NotEmpty(message = "自己紹介文は必ず入力してください")
   @Size(min = 50, max = 200, message = "自己紹介文は50文字以上200文字以内で入力してください")
   private String selfIntroduction;


}