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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .authorizeHttpRequests()
	            // LOGIN
	            .requestMatchers("/login").permitAll()
	            
	            // Registro de Cliente y Trabajador
	            .requestMatchers(HttpMethod.POST, "/cliente").permitAll()  // Cliente registration
	            .requestMatchers(HttpMethod.POST, "/trabajador").permitAll()  // Trabajador registration
	            
	            // MENSAJES (Messages)
	            .requestMatchers(HttpMethod.GET, "/mensaje/{id}").permitAll()
	            .requestMatchers(HttpMethod.GET, "/mensaje/actor/{id}").permitAll()
	            .requestMatchers(HttpMethod.POST, "/mensaje/{id}").authenticated()  // Messages only for authenticated users
	            .requestMatchers(HttpMethod.PUT, "/mensaje/{id}").authenticated()  
	            .requestMatchers(HttpMethod.DELETE, "/mensaje/{id}").authenticated()

	            // TAREA REPARACIÓN (Repair Tasks)
	            .requestMatchers(HttpMethod.GET, "/tareaReparacion").permitAll()
	            .requestMatchers(HttpMethod.GET, "/tareaReparacion/{id}").permitAll()
	            .requestMatchers(HttpMethod.POST, "/tareaReparacion/{id}").hasAuthority("CASETA")
	            .requestMatchers(HttpMethod.PUT, "/tareaReparacion/{id}").hasAuthority("CASETA")
	            .requestMatchers(HttpMethod.DELETE, "/tareaReparacion/{id}").hasAuthority("CASETA")

	            // SOLICITUD (Requests)
	            .requestMatchers(HttpMethod.GET, "/solicitud/accept/{id}").hasAuthority("AYUNTAMIENTO")
	            .requestMatchers(HttpMethod.GET, "/solicitud/refuse/{id}").hasAuthority("AYUNTAMIENTO")
	            .requestMatchers(HttpMethod.GET, "/solicitud/{id}").hasAnyAuthority("CASETA", "AYUNTAMIENTO")
	            .requestMatchers(HttpMethod.GET, "/solicitud/deCaseta").hasAuthority("AYUNTAMIENTO")
	            .requestMatchers(HttpMethod.GET, "/solicitud/deAyuntamiento").hasAuthority("AYUNTAMIENTO")
	            .requestMatchers(HttpMethod.POST, "/solicitud/{id}").hasAuthority("CASETA")
	            .requestMatchers(HttpMethod.DELETE, "/solicitud").hasAuthority("CASETA")

	            // CLIENTE (Client)
	            .requestMatchers(HttpMethod.GET, "/cliente").permitAll()
	            .requestMatchers(HttpMethod.GET, "/cliente/{id}").permitAll()
	            .requestMatchers(HttpMethod.POST, "/cliente").permitAll()
	            .requestMatchers(HttpMethod.PUT, "/cliente").hasAuthority("CLIENTE")  // Only client can edit their data
	            .requestMatchers(HttpMethod.DELETE, "/cliente").hasAuthority("CLIENTE")
	            
	            // TRABAJADOR (Worker)
	            .requestMatchers(HttpMethod.GET, "/trabajador").permitAll()
	            .requestMatchers(HttpMethod.GET, "/trabajador/{id}").permitAll()
	            .requestMatchers(HttpMethod.POST, "/trabajador").permitAll()
	            .requestMatchers(HttpMethod.PUT, "/trabajador").hasAuthority("TRABAJADOR")  // Only worker can edit their data
	            .requestMatchers(HttpMethod.DELETE, "/trabajador").hasAuthority("TRABAJADOR")

	            // ADMINISTRADOR (Administrator)
	            .requestMatchers("/admin").hasAuthority("ADMIN")
	            
	            // PATROCINADOR (Sponsor)
	            .requestMatchers(HttpMethod.GET, "/patrocinador").permitAll()
	            .requestMatchers(HttpMethod.GET, "/patrocinador/{id}").permitAll()
	            .requestMatchers(HttpMethod.POST, "/patrocinador").hasAnyAuthority("PATROCINADOR")
	            .requestMatchers(HttpMethod.PUT, "/patrocinador").hasAnyAuthority("PATROCINADOR")
	            .requestMatchers(HttpMethod.DELETE, "/patrocinador").hasAuthority("PATROCINADOR")
	            
	            // SWAGGER (API documentation)
	            .requestMatchers("/swagger-ui/**").permitAll()
	            .requestMatchers("/v3/api-docs/**").permitAll()
	            
	            // OTRAS RUTAS (Other Routes)
	            .anyRequest().authenticated();  // All other requests require authentication


			http.addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
		}

}
