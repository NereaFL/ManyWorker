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
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 @Bean
	    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.authorizeHttpRequests()
					// LOGIN
					.requestMatchers("/login").permitAll()
					
					// Registro de Cliente y Trabajador
		            .requestMatchers(HttpMethod.POST, "/cliente").permitAll()  // Registro de cliente
		            .requestMatchers(HttpMethod.POST, "/trabajador").permitAll()  // Registro de trabajador
		            
		            // MENSAJES
		            .requestMatchers(HttpMethod.GET, "/mensaje/{id}").permitAll()
		            .requestMatchers(HttpMethod.GET, "/mensaje/actor/{id}").permitAll()
		            .requestMatchers(HttpMethod.POST, "/mensaje/{id}").authenticated()  // Mensajes solo para autenticados
		            .requestMatchers(HttpMethod.PUT, "/mensaje/{id}").authenticated()  
		            .requestMatchers(HttpMethod.DELETE, "/mensaje/{id}").authenticated()

		            // TAREA REPARACIÓN
		            .requestMatchers(HttpMethod.GET, "/tareaReparacion").permitAll()
		            .requestMatchers(HttpMethod.GET, "/tareaReparacion/{id}").permitAll()
		            .requestMatchers(HttpMethod.POST, "/tareaReparacion/{id}").hasAuthority("CASETA")
		            .requestMatchers(HttpMethod.PUT, "/tareaReparacion/{id}").hasAuthority("CASETA")
		            .requestMatchers(HttpMethod.DELETE, "/tareaReparacion/{id}").hasAuthority("CASETA")

		            // SOLICITUD
		            .requestMatchers(HttpMethod.GET, "/solicitud/accept/{id}").hasAuthority("AYUNTAMIENTO")
		            .requestMatchers(HttpMethod.GET, "/solicitud/refuse/{id}").hasAuthority("AYUNTAMIENTO")
		            .requestMatchers(HttpMethod.GET, "/solicitud/{id}").hasAnyAuthority("CASETA", "AYUNTAMIENTO")
		            .requestMatchers(HttpMethod.GET, "/solicitud/deCaseta").hasAuthority("AYUNTAMIENTO")
		            .requestMatchers(HttpMethod.GET, "/solicitud/deAyuntamiento").hasAuthority("AYUNTAMIENTO")
		            .requestMatchers(HttpMethod.POST, "/solicitud/{id}").hasAuthority("CASETA")
		            .requestMatchers(HttpMethod.DELETE, "/solicitud").hasAuthority("CASETA")

		            // CLIENTE
		            .requestMatchers(HttpMethod.GET, "/cliente").permitAll()
		            .requestMatchers(HttpMethod.GET, "/cliente/{id}").permitAll()
		            .requestMatchers(HttpMethod.POST, "/cliente").permitAll()
		            .requestMatchers(HttpMethod.PUT, "/cliente").hasAuthority("CLIENTE") // Solo cliente puede editar sus datos
		            .requestMatchers(HttpMethod.DELETE, "/cliente").hasAuthority("CLIENTE")
		            
		            // TRABAJADOR
		            .requestMatchers(HttpMethod.GET, "/trabajador").permitAll()
		            .requestMatchers(HttpMethod.GET, "/trabajador/{id}").permitAll()
		            .requestMatchers(HttpMethod.POST, "/trabajador").permitAll()
		            .requestMatchers(HttpMethod.PUT, "/trabajador").hasAuthority("TRABAJADOR")  // Solo trabajador puede editar sus datos
		            .requestMatchers(HttpMethod.DELETE, "/trabajador").hasAuthority("TRABAJADOR")

		            // ADMINISTRADOR
		            .requestMatchers("/admin").hasAuthority("ADMIN")
		            
		            // PATROCINADOR
		            .requestMatchers(HttpMethod.GET, "/patrocinador").permitAll()
		            .requestMatchers(HttpMethod.GET, "/patrocinador/{id}").permitAll()
		            .requestMatchers(HttpMethod.POST, "/patrocinador").hasAnyAuthority("PATROCINADOR")
		            .requestMatchers(HttpMethod.PUT, "/patrocinador").hasAnyAuthority("PATROCINADOR")
		            .requestMatchers(HttpMethod.DELETE, "/patrocinador").hasAuthority("PATROCINADOR")
		            
		            // SWAGGER
		            .requestMatchers("/swagger-ui/**").permitAll()
		            .requestMatchers("/v3/api-docs/**").permitAll()

		            // OTRAS RUTAS
		            .anyRequest().authenticated();  

		    // Filtro JWT
		    http.addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		    return http.build();
		}

}
