package com.nttdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nttdata.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	List<Usuario> findAll();
	
	@Query("SELECT u FROM Usuario u WHERE email = ?1 and password = ?2")
	Usuario getIdLoginUser(String email, String password);
}
