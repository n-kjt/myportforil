package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報更新リクエストデータ
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateRequest extends UserAddRequest implements Serializable {

    /**
     * ユーザーID
     */
    @NotNull
    private Long id;
    
    /**
    * 自己紹介文
    */
   @Size(min = 50, max = 200, message = "自己紹介文は50文字以上200文字以内で入力してください")

   private String self_introduction;
}