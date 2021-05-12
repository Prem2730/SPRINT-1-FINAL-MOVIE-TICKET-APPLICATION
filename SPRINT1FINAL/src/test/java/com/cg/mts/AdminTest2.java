package com.cg.mts;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.Admin;
import com.cg.mts.repository.IUserRepository;
import com.cg.mts.service.IAdminService;

@SpringBootTest
public class AdminTest2 {

	@Autowired
	IAdminService service;
	
	@MockBean
	IUserRepository repository;
	
	public Admin getAdmin() {
		Admin admin = new Admin();
		admin.setAdminContact("7000000003");
		admin.setAdminName("Sai");
		admin.setPassword("Sai@123");
		admin.setRole("Admin");
		return admin;
	}
	
	@Test
	void testAddAdmin() {
		Admin adminData = getAdmin();
		Mockito.when(repository.existsByUserId(adminData.getUserId())).thenReturn(false);
		Mockito.when(repository.save(adminData)).thenReturn(adminData);
		Admin res = service.addNewAdmin(adminData);
//		assertEquals(adminData.getUserId(), res.getUserId());
	}
	
	@Test
	void testDeleteAdmin() {
		Admin admin = getAdmin();
		int id = admin.getUserId();
		Mockito.when(repository.existsByUserId(id)).thenReturn(true);
		service.deleteAdmin(id);
		verify(repository, times(1)).existsByUserId(id); 
	}
}
