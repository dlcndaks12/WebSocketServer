package com.almond.ourproject.main.dao;
import java.util.HashMap;
import org.springframework.stereotype.Repository;

import com.almond.ourproject.common.dao.AbstractDAO;

@Repository
public class MainDAO extends AbstractDAO {

	public HashMap<String, Object> test(HashMap<String, Object> param) {
		return (HashMap) selectOne("MainDAO.test", param);
	}
}
