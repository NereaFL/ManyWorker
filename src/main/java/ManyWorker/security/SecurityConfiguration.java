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
				// Productos
				.requestMatchers(HttpMethod.POST, "/producto").hasAuthority("CASETA")
				.requestMatchers(HttpMethod.PUT, "/producto").hasAuthority("CASETA")
				.requestMatchers(HttpMethod.GET, "/producto").hasAuthority("SOCIO")
				// Cliente
				.requestMatchers("/cliente").hasAuthority("CLIENTE")
				// Administrador
				.requestMatchers("/administrador").hasAuthority("ADMINISTRADOR")
				// Patrocinador
				.requestMatchers("/patrocinador").hasAuthority("PATROCINADOR")
				// Trabajador
				.requestMatchers("/trabajador").hasAuthority("TRABAJADOR")
				// Login
				.requestMatchers("/login").permitAll()
				.anyRequest().permitAll();

		http.addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
