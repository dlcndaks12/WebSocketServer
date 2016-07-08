package com.almond.ourproject.main.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almond.ourproject.main.dao.MainDAO;

@Service
public class MainService {
	
	@Autowired
	private MainDAO mainDAO;
	
	public HashMap<String, Object> test(HashMap<String, Object> param) {
		return mainDAO.test(param);
	}
}
