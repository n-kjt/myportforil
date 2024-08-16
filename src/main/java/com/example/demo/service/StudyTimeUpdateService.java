package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.LearningDataMapper;
import com.example.demo.dto.StudyTimeUpdateRequest;

@Service
public class StudyTimeUpdateService {
    private final LearningDataMapper learningDataMapper;

    public StudyTimeUpdateService(LearningDataMapper learningDataMapper) {
        this.learningDataMapper = learningDataMapper;
    }

    @Transactional
    public void updateStudyTime(StudyTimeUpdateRequest request) {
        learningDataMapper.updateStudyTime(request);
    }
}
