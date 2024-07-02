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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserUpdateRequest;
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
     * 新規登録画面を表示
     * @param model Model
     * @return 新規登録画面を表示
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
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            // エラーメッセージをモデルに追加
            model.addAttribute("error_message", "メールアドレス、もしくはパスワードが間違っています");
        }
        return "user/login";
    }
    
    /**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー新規登録
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
        userInfoService.save(userRequest);
	    System.out.println(userRequest);
	    return "redirect:/user/top";
	}
    /**
     * 自己紹介更新ページを表示
     */
	@RequestMapping("/user/profileedit")
	public String profileedit(Model model) {
	    model.addAttribute("userUpdateRequest", new UserUpdateRequest());
	    return "/user/profileedit";
	} 
    
    /**
     * 自己紹介更新
     * @param userRequest リクエストデータ
     * @param model Model
     * @return 自己紹介更新
     */
    @RequestMapping(value = "/user/profileedit", method = RequestMethod.POST)
    public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "user/profileedit";
        }
        // ユーザー情報の更新
        userInfoService.update(userUpdateRequest);
        return "redirect:/user/top";
    }
   
}