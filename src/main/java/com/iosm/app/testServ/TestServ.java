package com.iosm.app.testServ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iosm.app.rep.ContactDirectoryRepInt;
import com.iosm.app.vo.UserContact;

public class TestServ implements TestServInt{
	
	@Autowired
	ContactDirectoryRepInt rep;

	@Override
	public List<UserContact> getListUsert() {
		return rep.findAll();
	}

	@Override
	public void save(UserContact saveList) {
		rep.save(saveList);
	}

	@Override
	public void delete(Long id) {
		rep.deleteById(id);		
	}
	
}
