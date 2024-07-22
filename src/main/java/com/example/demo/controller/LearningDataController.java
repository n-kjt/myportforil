package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.LearningDataMapper;

/**
 * 学習データ情報 Controller
 */
@Controller
public class LearningDataController {

    private final LearningDataMapper learningDataMapper;

    //@Autowired
    public LearningDataController(LearningDataMapper learningDataService) {
        this.learningDataMapper = learningDataService;
    }

    @GetMapping("/user/category")
    public String getLearningData(Model model) {
        model.addAttribute("groupedByCategory", learningDataMapper.getLearningData());
        return "/user/category";
    }

}