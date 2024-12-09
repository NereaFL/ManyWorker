package ManyWorker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private JWTAuthenticationFilter JWTAuthenticationFilter;

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
				// LOGIN
				.requestMatchers("/login").permitAll()

				// MENSAJES
				.requestMatchers(HttpMethod.GET, "/mensaje/deTrabajador").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.GET, "/mensaje/deCliente").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.GET, "/mensaje/deAdministrador").hasAuthority("ADMINISTRADOR")
				.requestMatchers(HttpMethod.GET, "/mensaje/dePatrocinador").hasAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.GET, "/mensaje/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/mensaje").permitAll()
				.requestMatchers(HttpMethod.POST, "/mensaje/broadcast").hasAuthority("ADMINISTRADOR")

				// TAREA REPARACIÓN
				.requestMatchers(HttpMethod.GET, "/tareaReparacion/deCliente").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.GET, "/tareaReparacion/{id}").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.GET, "/tareaReparacion").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.POST, "/tareaReparacion").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.PUT, "/tareaReparacion/{id}").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.DELETE, "/tareaReparacion/{id}").hasAuthority("CLIENTE")

				// SOLICITUD
				.requestMatchers(HttpMethod.GET, "/solicitud/accept/{id}").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.GET, "/solicitud/refuse/{id}").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.GET, "/solicitud/{id}").hasAnyAuthority("CLIENTE", "TRABAJADOR")
				.requestMatchers(HttpMethod.GET, "/solicitud/deTrabajador").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.GET, "/solicitud/deCliente").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.POST, "/solicitud/{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.DELETE, "/solicitud/{id}").hasAuthority("TRABAJADOR")
				
				// CATEGORIA
				.requestMatchers(HttpMethod.GET, "/categoria").hasAuthority("ADMINISTRADOR")
				.requestMatchers(HttpMethod.GET, "/categoria/{id}").hasAuthority("ADMINISTRADOR")
				.requestMatchers(HttpMethod.POST, "/categoria").hasAuthority("ADMINISTRADOR")
				.requestMatchers(HttpMethod.PUT, "/categoria/{id}").hasAuthority("ADMINISTRADOR")
				.requestMatchers(HttpMethod.DELETE, "/categoria/{id}").hasAuthority("ADMINISTRADOR")
				
				//CURRICULO
				.requestMatchers(HttpMethod.GET, "/curriculo").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.GET, "/curriculo/{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.POST, "/curriculo").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.PUT, "/curriculo/{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.DELETE, "/curriculo{id}").hasAuthority("TRABAJADOR")

				// PLAN DE TRBAJO
				.requestMatchers(HttpMethod.GET, "/planTrabajo").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.GET, "/planTrabajo/{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.POST, "/planTrabajo").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.PUT, "/planTrabajo/{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.DELETE, "/planTrabajo{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.DELETE, "planTrabajo/fases/{id}").hasAuthority("TRABAJADOR")
				
				// TUTORIAL
				.requestMatchers(HttpMethod.GET, "/tutorial").permitAll()
				.requestMatchers(HttpMethod.GET, "/tutorial/trabajador/{trabajadorId}").permitAll()
				.requestMatchers(HttpMethod.GET, "/tutorial/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/tutorial").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.PUT, "/tutorial{id}").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.DELETE, "/tutorial/{id}").hasAuthority("TRABAJADOR")
				
				// PATROCINIO
				.requestMatchers(HttpMethod.GET, "/patrocinio").hasAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.GET, "/patrocinio/{id}").hasAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.POST, "/patrocinio").hasAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.PUT, "/patrocinio{id}").hasAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.DELETE, "/patrocinio/{id}").hasAuthority("PATROCINADOR")
				
				// CLIENTE
				.requestMatchers(HttpMethod.GET, "/cliente").permitAll()
				.requestMatchers(HttpMethod.GET, "/cliente/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/cliente").permitAll()
				.requestMatchers(HttpMethod.PUT, "/cliente").hasAuthority("CLIENTE")
				.requestMatchers(HttpMethod.DELETE, "/cliente").hasAuthority("CLIENTE")

				// TRABAJADOR
				.requestMatchers(HttpMethod.GET, "/trabajador").permitAll()
				.requestMatchers(HttpMethod.GET, "/trabajador/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/trabajador").permitAll()
				.requestMatchers(HttpMethod.PUT, "/trabajador").hasAuthority("TRABAJADOR")
				.requestMatchers(HttpMethod.DELETE, "/trabajador").hasAuthority("TRABAJADOR")

				// ADMINISTRADOR
				.requestMatchers("/admin").hasAuthority("ADMINISTRADOR")

				// PATROCINADOR
				.requestMatchers(HttpMethod.GET, "/patrocinador").permitAll()
				.requestMatchers(HttpMethod.GET, "/patrocinador/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/patrocinador").permitAll()
				.requestMatchers(HttpMethod.PUT, "/patrocinador").hasAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.DELETE, "/patrocinador").hasAuthority("PATROCINADOR")

				// SWAGGER
				.requestMatchers("/swagger-ui/**").permitAll().requestMatchers("/v3/api-docs/**").permitAll()
				

				// OTRAS RUTAS
				.anyRequest().authenticated();

		http.addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
