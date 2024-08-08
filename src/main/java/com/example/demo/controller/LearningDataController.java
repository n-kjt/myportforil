package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.dao.LearningDataMapper;
import com.example.demo.dto.LearningDataUpdateRequest;
import com.example.demo.service.LearningDataService;

/**
 * 学習データ情報 Controller
 */
@Controller
public class LearningDataController {

    private final LearningDataMapper learningDataMapper;
    private final LearningDataService learningDataService;

    @Autowired
    public LearningDataController(LearningDataMapper learningDataMapper, LearningDataService leaningDataService) {
        this.learningDataMapper = learningDataMapper;
        this.learningDataService = leaningDataService;  
        }

//    ページに項目と時間を表示
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
    public String skillEdit(@RequestParam("category_id") int categoryId, Model model) {
        // 現在認証されているユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // LearningDataUpdateRequestオブジェクトを作成し、categoryIdを設定
        LearningDataUpdateRequest learningDataUpdateRequest = new LearningDataUpdateRequest();
        learningDataUpdateRequest.setCategoryId(categoryId);
        learningDataUpdateRequest.setUserId(userDetails.getId()); // ユーザーIDを設定
        
        //カテゴリー名を表示
        String categoryName = learningDataService.findCategoryName(categoryId);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("learningDataUpdateRequest", learningDataUpdateRequest);

        return "/user/skilledit";
    }

    
    @PostMapping("/user/skilledit")//サーバーの変更時はPostMappingを使用する
    public String insert(@Validated @ModelAttribute("learningDataUpdateRequest") LearningDataUpdateRequest learningDataUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	
            // カテゴリ名をモデルに追加
            String categoryName = learningDataService.findCategoryName(learningDataUpdateRequest.getCategoryId());
            model.addAttribute("categoryName", categoryName);
            
            return "/user/skilledit";
        }
        // 現在認証されているユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // ユーザーIDを設定
        learningDataUpdateRequest.setUserId(userDetails.getId());
        
        // 学習項目の重複チェック
        if (learningDataService.isStudyNameDuplicate(learningDataUpdateRequest.getStudyName(), userDetails.getId())) {
        	//エラーメッセージの表示
        	String errorMessage = String.format("%sは既に登録されています", learningDataUpdateRequest.getStudyName());
        	result.rejectValue("studyName", "error.studyName", errorMessage);
        	
            // カテゴリ名をモデルに追加
            String categoryName = learningDataService.findCategoryName(learningDataUpdateRequest.getCategoryId());
            model.addAttribute("categoryName", categoryName);
            
            return "/user/skilledit";
        }        
        
        // 学習項目の追加
        learningDataMapper.insertLearningData(learningDataUpdateRequest);
        return "redirect:/user/category";

    }

}
