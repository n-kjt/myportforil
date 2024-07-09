//8:画像表示のためにコントローラー作成
package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

	@GetMapping("/")
	public String getIndex() {
		return "image";
	}
	
    @RequestMapping("/getImg")
    @ResponseBody
    public HttpEntity<byte[]> getImg(@RequestParam("name") String fileName) {
        File fileImg = new File("img/" + fileName + ".png"); // 画像フォルダのディレクトリを指定

        byte[] byteImg;
        try {
            // バイト列に変換
            byteImg = Files.readAllBytes(fileImg.toPath());
        } catch (IOException e) {
            // 例外発生時にエラーメッセージを返す
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new HttpEntity<>(byteImg);
    }
	
}
