package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.service.UserInfoService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserInfoController {

    /**
     * ユーザー情報 Service
     */
    @Autowired
    private UserInfoService userInfoService;



    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @GetMapping(value = "/user/add")
    public String displayAdd(Model model) {
        model.addAttribute("userAddRequest", new UserAddRequest());
        return "user/add";
    }


    
    /**
     * ユーザーページトップを表示
     */
    @GetMapping("/user/top")
    public String top() {
        return "/user/top";
    }  
    
    /**
     * ログインページを表示
     */
    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }  
    
    /**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // 入力チェックエラーの場合
	        List<String> errorList = new ArrayList<String>();
	        for (ObjectError error : result.getAllErrors()) {
	            errorList.add(error.getDefaultMessage());
	        }
	        model.addAttribute("validationError", errorList);
	        
	        //コンソールで出力状況を確認
	        System.out.println(model.getAttribute("validationError"));
	        
	        return "user/add";
	    }
	    // ユーザー情報の登録
	//        userInfoService.save(userRequest);
	    System.out.println(userRequest);
	    return "redirect:/user/top";
	}

   
}