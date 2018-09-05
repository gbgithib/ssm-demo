package com.bingo.service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bingo.dao.TestDao;
import com.bingo.service.TestService;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	@Override
	public Map<String, String> getList() {
		// TODO Auto-generated method stub
		return testDao.getList();
	}

}
