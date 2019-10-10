package com.iosm.app.testServ;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iosm.app.vo.UserContact;

@Service
public interface TestServInt {
	
	public List<UserContact> getListUsert();
	
	public void save(UserContact saveList);

	public void delete(Long id);
	

}
