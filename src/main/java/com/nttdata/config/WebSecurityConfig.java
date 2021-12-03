package com.nttdata.config;

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
 			.antMatchers("/", "/home", "login", "/registro", "/usuario/registrar", "/usuario/login")
 			.permitAll()
 			.antMatchers("/usuario", "/producto/producto_admin", "/venta","/categoria/categoria_admin") 
 			.hasAuthority("ROLE_ADMIN")
 			.antMatchers("/producto", "/carrito/**","/categoria/**")
 			.hasAuthority("ROLE_USER")
 			.antMatchers("/logout", "/usuario/login")
 			.permitAll()
 			.anyRequest().authenticated()
// 			.anyRequest()
// 			.authenticated()
 		.and()
 		.formLogin()
 			.loginPage("/login") 
 			.loginProcessingUrl("/usuario/login")
 			.defaultSuccessUrl("/home")
// 			.failureUrl("/access_denied")
	 		.permitAll()
 			.usernameParameter("email")
 			.passwordParameter("password");
// 		.and()
//	      .logout()
//	      .logoutUrl("/perform_logout")
//	      .deleteCookies("JSESSIONID")
//	      .logoutSuccessHandler(logoutSuccessHandler());
// 			.permitAll();
		
//		http.authorizeRequests()
//		.antMatchers("/home","/carrito/**").hasAuthority("ROLE_USER")
//		.antMatchers("/producto/**","/usuario/**").hasAuthority("ROLE_ADMIN")
//		.antMatchers("/login","/registro").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login")
//		.successHandler(customAuthenticationSuccessHandler)
//		.failureUrl("/login?error=true")
//		.usernameParameter("email").passwordParameter("password");
//		http.headers().cacheControl();
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
