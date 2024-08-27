package com.example.demo.dto;

import lombok.Data;

/**
 * 学習データのカテゴリ別かつ月別の合計時間データ
 *
 */

@Data //エンティティ：内のゲッターとセッター定義を省略できる
public class LearningDataTotalRequest {
	 private Long userId;
	    private Long categoryId;
	    private String month;
	    private int totalStudyTime;

	    // GetterとSetter
	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public Long getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(Long categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getMonth() {
	        return month;
	    }

	    public void setMonth(String month) {
	        this.month = month;
	    }

	    public int getTotalStudyTime() {
	        return totalStudyTime;
	    }

	    public void setTotalStudyTime(int totalStudyTime) {
	        this.totalStudyTime = totalStudyTime;
	    }
	}
