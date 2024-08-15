package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.LearningDataUpdateRequest;
import com.example.demo.entity.LearningData;

@Mapper
public interface LearningDataMapper {
	

    /**
     * カテゴリ別に学習情報を検索
     * @return 検索結果
     */
    List<LearningData> getLearningData(@Param("userId") long userId);
    
    
    /**
     * 学習項目の重複がないかを確認するためのメソッド
     */
    int countByStudyName(@Param("studyName") String studyName, @Param("userId") long userId);
    
    
    // 学習時間を登録
    void insertLearningData(LearningDataUpdateRequest learningDataUpdateRequest);
    
    //カテゴリ名を取得
    String findCategoryName(int categoryId);
    
    
    // 学習時間を更新
    void updateStudyTime(LearningDataUpdateRequest learningDataUpdateRequest);

//    //学習情報の論理削除
//    void deleteStudyItem(Long id);
//  
}