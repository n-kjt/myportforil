package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LearningDataMapper;
import com.example.demo.dto.LearningDataUpdateRequest;

@Service
public class LeaningDataService {
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
	

    
    }

