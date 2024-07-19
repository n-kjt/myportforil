package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.LearningDataService;

/**
 * 学習データ情報 Controller
 */
@Controller
public class LearningDataController {

    private final LearningDataService learningDataService;

    //@Autowired
    public LearningDataController(LearningDataService learningDataService) {
        this.learningDataService = learningDataService;
    }

    @GetMapping("/user/category")
    public String getLearningData(Model model) {
        model.addAttribute("groupedByCategory", learningDataService.getAllLearningData());
        return "/user/category";
    }

}