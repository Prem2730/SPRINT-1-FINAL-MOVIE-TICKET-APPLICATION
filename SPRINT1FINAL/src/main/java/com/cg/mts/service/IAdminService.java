package com.cg.mts.service;

import com.cg.mts.entities.Admin;
import com.cg.mts.exception.AdminExistsException;
import com.cg.mts.exception.AdminNotFoundException;

public interface IAdminService {
	public Admin addNewAdmin(Admin admin) throws AdminExistsException;
	public void deleteAdmin(int userId) throws AdminNotFoundException;
}
