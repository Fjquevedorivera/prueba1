package com.nttdata.services;

import java.util.List;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.models.Usuario;
import com.nttdata.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void insertarUsuario(@Valid Usuario usuario) {
		usuarioRepository.save(usuario);	
	}
	
	public void actualizarUsuario(@Valid Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public List<Usuario> obtenerListaUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarUsuarioId(Long id) {
		return usuarioRepository.findById(id).get();
	}

	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public void eliminarUsuarioObj(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public Usuario getIdLoginUser(String email, String password) {
		return usuarioRepository.getIdLoginUser(email, password);
	}
	
	public boolean loginUsuario(String email, String password) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if(usuario == null) {
			return false;
		} else {
//			if(password.equals(usuario.getPassword())) {
			if(BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			} else {
				return false;
			}
		} 

	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario findByName(String nombre) {
		return usuarioRepository.findByName(nombre);
	}
	
}
