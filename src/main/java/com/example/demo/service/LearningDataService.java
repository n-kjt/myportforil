package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LearningDataMapper;
import com.example.demo.dto.LearningDataUpdateRequest;

@Service
public class LearningDataService {
    /**
     * 学習項目追加 Mapper
     */
    @Autowired
    private LearningDataMapper learningDataMapper;
    
    /**
     * 学習項目追加
     */
    public void insert(LearningDataUpdateRequest learningDataUpdateRequest) {
    	learningDataMapper.insertLearningData(learningDataUpdateRequest);
    }
    
    /**
     * カテゴリ名を取得
     */
    public String findCategoryName(int categoryId) {
        return learningDataMapper.findCategoryName(categoryId);
    }
    
    /**
     * 学習項目名の重複をチェック
     */    
    
    public boolean isStudyNameDuplicate(String studyName, long userId) {
        return learningDataMapper.countByStudyName(studyName, userId) > 0;
    }
    

    
    }

    }

