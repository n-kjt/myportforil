package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.dao.LearningDataMapper;
import com.example.demo.dto.StudyTimeUpdateRequest;
import com.example.demo.service.StudyTimeUpdateService;

/**
 * 学習データ情報 Controller
 */
@Controller
public class LearningDataController {

    private final LearningDataMapper learningDataMapper;
    private final StudyTimeUpdateService studyTimeUpdateService;
    
    //@Autowired
    public LearningDataController(LearningDataMapper learningDataService,StudyTimeUpdateService studyTimeUpdateService) {
        this.learningDataMapper = learningDataService;
        this.studyTimeUpdateService = studyTimeUpdateService;
    }


//    学習項目の追加
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
    
//    学習項目の変更と削除
    @RequestMapping(value="/user/category", method=RequestMethod.POST)      
    	public String updateStudyTime(@ModelAttribute StudyTimeUpdateRequest studyTimeUpdateRequest,@RequestParam String action,Model model,Authentication authentication) {
    		
        if ("update".equals(action)) {
            studyTimeUpdateService.updateStudyTime(studyTimeUpdateRequest);}
        else if ("delete".equals(action)) {
            studyTimeUpdateService.deleteData(studyTimeUpdateRequest);
        }
        
    		// 成功ページへリダイレクト
    	        return "redirect:/user/category";
    }

    
}