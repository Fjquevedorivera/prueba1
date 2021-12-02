package com.nttdata.config;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService usd;
//	UsuarioDetailsServiceImplementation udsi;
	
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
		//Configuracion basica
 		// 1. auth: Restrige el acceso a peticiones request mediante url
		// 2. ant: Definir rutas permitidas css/**, js/**, /registro
		// 3. has: seleccionar rol 
		// 4. form: permiso a login autogenerado
		// 4. .loginPage() // para definir login personalisado
		// .permitAll() // permite a todos acceso a la ruta mientras tenga un rol USER
		// anyRequest cualquier ruta autehntica debe estar autenticado para cualquier ruta
		http.authorizeRequests()
 			.antMatchers("/registro","/usuario/registrarjsp","/","/usuario/registrar")
 			.permitAll()
 			.anyRequest().authenticated()
// 			.hasRole("USER")
 		.and()
 		.formLogin()
 			.loginPage("/login") 
	 		.permitAll()
 			.usernameParameter("email")
 			.passwordParameter("password")
 		.and()
 		.logout()
 			.permitAll();
 	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usd).passwordEncoder(bCryptPasswordEncoder());
	}
}
