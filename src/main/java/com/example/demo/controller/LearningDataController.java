package com.example.demo.controller;

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

        // モデルに追加
        model.addAttribute("learningDataUpdateRequest", learningDataUpdateRequest);

        return "/user/skilledit";
    }

    
    
    @PostMapping("/user/skilledit")
    public String insert(@Validated @ModelAttribute("learningDataUpdateRequest") LearningDataUpdateRequest learningDataUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/skilledit";
        }
        // 現在認証されているユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // ユーザーIDを設定
        learningDataUpdateRequest.setUserId(userDetails.getId());
        
        //カテゴリー名を表示
//        LearningData categoryName = LeaningDataService.findCategory(category_id);
//        model.addAttribute("category_name", categoryName);
        
        // 学習項目の追加
        learningDataMapper.insertLearningData(learningDataUpdateRequest);
        return "redirect:/user/category";

                
        // 重複チェック
//        if (learningDataMapper.countByStudyName(userUpdateRequest.getStudyName(), userDetails.getId()) > 0) {
//            result.rejectValue("studyName", "error.studyName", "同じ名前の項目が既に存在します");
//            return "/user/skilledit";
//        }


    }
    

}
