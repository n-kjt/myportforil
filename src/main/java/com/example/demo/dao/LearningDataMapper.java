package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.LearningData;

@Mapper
public interface LearningDataMapper {
	
    /**
     * 学習情報全件検索
     * @param user 検索用リクエストデータ
     * @return 検索結果
     */
	List<LearningData> findAll();
    
    
}