package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザー情報登録 リクエストデータ
 */
@Data
public class UserAddRequest implements Serializable {
    /**
     * 氏名
     */
    @NotEmpty(message = "名前は必ず入力してください")
    @Size(max = 100, message = "名前は100桁以内で入力してください")
    private String name;
    /**
     * メールアドレス
     */
    @NotEmpty(message = "メールアドレスは必ず入力してください")
    @Size(max = 255, message = "メールアドレスは255桁以内で入力してください")
    private String email;
    /**
     * パスワード
     */
    @NotEmpty(message = "パスワードは必ず入力してください")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "英数字8文字以上で入力してください")
    private String password;
    /**
     * 自己紹介文
     */
    @Min(value = 50, message = "自己紹介文は50文字以上で入力してください")
    @Max(value = 200, message = "自己紹介文は200文字以内で入力してください")
    private String self_introduction;
}