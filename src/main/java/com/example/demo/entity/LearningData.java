package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

/**
 * 学習データ Entity
 */

@Table(name = "learning_data")
public class LearningData implements Serializable {

    private Long id;
    private String name;
    private int study_time;
    private Long category_id;
    private Long user_id;
    private Date created_at;
    private Date updated_at;
    private String category_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStudyName() {
        return name;
    }

    public void setStudyName(String studyName) {
        this.name = studyName;
    }
    
    public int getStudyTime() {
        return study_time;
    }

    public void setStudyTime(int studyTime) {
        this.study_time = studyTime;
    }

    public Long getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Long categoryId) {
        this.category_id = categoryId;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date createdAt) {
        this.created_at = createdAt;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updated_at = updatedAt;
    }

	public String getCategoryName() {
		return category_name;
	}

	public void setCategoryName(String categoryName) {
		this.category_name = categoryName;
	}

}
