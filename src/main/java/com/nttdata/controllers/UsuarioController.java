package com.nttdata.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Usuario;
import com.nttdata.services.ProductoService;
import com.nttdata.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired 
	UsuarioService usuarioService;
	
	@Autowired 
	ProductoService productoService;
	
	@RequestMapping("")
	public String usuario(@ModelAttribute("usuario") Usuario usuario,
			Model model) {
		model.addAttribute("listaUsuarios", usuarioService.obtenerListaUsuarios());
		return "usuario/usuario.jsp";
	}
	
	@RequestMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("usuario") Usuario usuario) {
		usuarioService.insertarUsuario(usuario);
		return "redirect:/usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
		Usuario usuario_existe = usuarioService.findByEmail(usuario.getEmail());

		//encript password
		if(usuario_existe == null) {
			if(usuario.getPassword() == usuario.getPasswordConfirmation()) {
				usuarioService.persistirUsuarioRol(usuario);
				return "redirect:/login";
			} else {
				model.addAttribute("error", "Contraseña no coincide");
				System.out.println("Contraseña no coincide");
			}
		} else {
			System.out.println("Usuario existe");
		}
//		usuarioService.registroUsuario(usuario);
		return "usuario/registro.jsp";
	}
	
//	@RequestMapping("/login")
//	public String login(@ModelAttribute("usuario") Usuario usuario, HttpSession session) {
//		System.out.println(usuario.getId());
//		session.setAttribute("usuarioLogin", null);
//		session.setAttribute("carro", null);
//		session.setAttribute("precioTotal", null);
//		return "usuario/login.jsp";
//	}b
	
	@RequestMapping("/login")
	public String login(Principal principal, Model model, HttpSession session) {
		
//		String nombre = principal.getName();
		
//		System.out.println(nombre);
//		Usuario usuario = usuarioService.findByName(nombre);
		session.setAttribute("carro", null);
		session.setAttribute("precioTotal", null);
//		model.addAttribute("nombre_usuario", usuario.getName());
//		model.addAttribute("listaProductos", productoService.obtenerListaProducto());
		model.addAttribute("error", null);
		return "home.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(@ModelAttribute("usuario") Usuario usuario, HttpSession session) {
		System.out.println(usuario.getId());
		session.setAttribute("carro", null);
		session.setAttribute("precioTotal", null);
		return "redirect:/logout";
	}
	
	@RequestMapping("/iniciar")
	public String iniciar(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
		Usuario usuarioLogin = usuarioService.getIdLoginUser(usuario.getEmail(), usuario.getPassword());
		
		if(usuarioLogin != null) {
			System.out.println(usuarioLogin.getId());
			session.setAttribute("usuarioLogin", usuarioLogin);
			model.addAttribute("usuarioLogin", usuarioLogin);

			return "redirect:/producto";
		} else {
			model.addAttribute("error", "Usuario y contraseña incorrectos");
			return "usuario/login.jsp";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminarObj(@RequestParam("id") Long id) {
		
		System.out.println("Id: "+ id);
		Usuario usuario = usuarioService.buscarUsuarioId(id);
		
		if(usuario != null) {
			usuarioService.eliminarUsuarioObj(usuario);
		}

		return "redirect:/usuario";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, 
			Model model, 
			HttpSession session) {
		
		System.out.println("Id: "+ id);
		Usuario usuario = usuarioService.buscarUsuarioId(id);
		
		if(usuario != null) {
			session.setAttribute("usuarioEditar", usuario);
			model.addAttribute("usuarioActualizar", usuario);
		}

		return "usuario/usuarioEditar.jsp";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute("usuarioActualizar") Usuario usuario, 
			Model model, 
			HttpSession session) {
		
		Usuario usuarioAntiguo = (Usuario)session.getAttribute("usuarioEditar");
		
		if(usuarioAntiguo != null) {
			usuarioAntiguo.setName(usuario.getName());
			usuarioAntiguo.setLast_name(usuario.getLast_name());
			usuarioAntiguo.setEmail(usuario.getEmail());
			usuarioAntiguo.setPassword(usuario.getPassword());
			
			usuarioService.actualizarUsuario(usuarioAntiguo);
			return "redirect:/usuario";
		}
		
		return "Usuario Incorrecto";
	}
	
}
