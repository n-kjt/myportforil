package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.LearningData;

@Mapper
public interface LearningDataMapper {
	

    /**
     * カテゴリ別に学習情報を検索
     * @return 検索結果
     */
    List<LearningData> getLearningData(@Param("userId") long userId);
    
    
}