package com.nttdata.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.models.Role;
import com.nttdata.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> getListRole(String nombre){
		return roleRepository.findByNombre(nombre);
	}
	
}
