package com.adityaprojects.photoapp.api.content.io.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adityaprojects.photoapp.api.content.ui.model.Message;

@Controller
public class ContentController {

	@Autowired
	private Message message;
	
	@GetMapping(path = "/message")
	@ResponseBody
	public String message() {
		
		return message.get();
	}
}
