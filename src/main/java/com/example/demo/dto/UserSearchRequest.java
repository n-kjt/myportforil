package com.example.demo.dto;

import java.io.Serializable;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Data
public class UserSearchRequest implements Serializable {

    /**
     * ユーザーID
     */
	@Column(value = "id")
    @NotNull
    private Long id;

    /**
     * ユーザー名
     */
    private String name;
}