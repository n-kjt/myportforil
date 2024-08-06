package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.dao.LearningDataMapper;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.LearningData;

import jakarta.validation.Valid;

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
        // 現在認証されているユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // ユーザーIDをモデルに追加
        model.addAttribute("userId", userDetails.getId());

        // 学習データをカテゴリ別にグループ化してモデルに追加
        model.addAttribute("groupedByCategory", learningDataMapper.getLearningData(userDetails.getId()));

        return "/user/category";
    }

    @GetMapping("/user/skilledit")
    public String skillEdit(Model model) {
        model.addAttribute("userUpdateRequest", new UserUpdateRequest());
        return "/user/skilledit";
    }

    @PostMapping("/user/skilledit")
    public String submitSkill(@Valid @ModelAttribute("userUpdateRequest") UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/skilledit";
        }

        // 現在認証されているユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // 重複チェック
        if (learningDataMapper.countByStudyName(userUpdateRequest.getStudyName(), userDetails.getId()) > 0) {
            result.rejectValue("studyName", "error.studyName", "同じ名前の項目が既に存在します");
            return "/user/skilledit";
        }

        // LearningDataエンティティにマッピングしてデータベースに保存
        LearningData learningData = new LearningData();
        learningData.setStudyName(userUpdateRequest.getStudyName());
        learningData.setStudyTime(userUpdateRequest.getStudyTime());
        learningData.setUserId(userDetails.getId());
//        learningData.setCategoryId(1); 

        // データベースに保存
        learningDataMapper.insertLearningData(learningData);

        // フォームデータを処理するロジックを追加
        return "redirect:/user/category"; // 成功時のリダイレクト先を指定
    }
}
