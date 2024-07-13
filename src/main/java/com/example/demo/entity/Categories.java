package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.relational.core.mapping.Table;

    /**
     * カテゴリ
     */

@Table(name = "categories")
public class Categories implements Serializable {

    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}