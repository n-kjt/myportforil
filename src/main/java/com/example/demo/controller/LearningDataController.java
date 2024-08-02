package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.authentication.CustomUserDetails;
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
    public String getLearningData(Model model) {//getLearningDataはSQLから情報を引っ張ってきている
        // 現在認証されているユーザーを取得
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        
        // ユーザーIDをモデルに追加(modelに追加するとHTML内で使えるようになる)
        model.addAttribute("userId", userDetails.getId());// userDetails.getId()をuserIdという名前で使えるようにする
        
        // 学習データをカテゴリ別にグループ化してモデルに追加
        model.addAttribute("groupedByCategory", learningDataMapper.getLearningData(userDetails.getId()));
        
        return "/user/category";
    }
}