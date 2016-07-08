package com.almond.ourproject.main.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.almond.ourproject.main.service.MainService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping(value = "/main.do")
	public ModelAndView main(@RequestParam HashMap<String, Object> param) {
		ModelAndView mv = new ModelAndView("main/main");
		
		HashMap<String, Object> map = mainService.test(param);
		
		return mv;
	}
}
