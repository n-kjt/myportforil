package com.example.demo.dto;

import java.io.Serializable;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.Min;
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
public class LearningDataUpdateRequest implements Serializable {
/**
   * スキル項目追加更新
   */
  @Column(value = "study_name")
  @NotEmpty(message = "項目名は必ず入力してください")
  @Size(max = 50, message = "項目名は50文字以内で入力してください")
  private String studyName;
  
  /**
  * 学習時間更新
  */
  @Column(value = "study_time")
  @NotNull(message = "学習時間は必ず入力してください")
  @Min(value = 1, message = "学習時間は1以上の数字で入力してください")
  
  private Integer studyTime;
  private Integer category_id;
  private String category_name;
  private Long user_id;
  
    public Long getUserId() {
        return user_id;
    }
    public void setUserId(Long userId) {
        this.user_id = userId;
    }
    
    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Integer categoryId) {
        this.category_id = categoryId;
    }
    
    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String categoryName) {
        this.category_name = categoryName;
    }
    
  
}