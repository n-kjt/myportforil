package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LearningDataMapper;
import com.example.demo.entity.LearningData;

/**
 * 学習データ情報 Service
 */
@Service
public class LearningDataService {

    @Autowired
    private LearningDataMapper learningDataMapper;

    public LearningDataService(LearningDataMapper learningDataMapper) {
        this.learningDataMapper = learningDataMapper;
    }

    public List<LearningData> getAllLearningData() {
        return learningDataMapper.findAll();
    }

}