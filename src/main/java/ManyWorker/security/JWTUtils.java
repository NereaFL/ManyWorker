package ManyWorker.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import ManyWorker.entity.Actor;
import ManyWorker.entity.Administrador;
import ManyWorker.entity.Cliente;
import ManyWorker.entity.Patrocinador;
import ManyWorker.entity.Trabajador;
import ManyWorker.service.ActorService;
import ManyWorker.service.AdministradorService;
import ManyWorker.service.ClienteService;
import ManyWorker.service.PatrocinadorService;
import ManyWorker.service.TrabajadorService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

public class JWTUtils {
	private static final String JWT_FIRMA = "JaviSBC";
	private static final long EXTENCION_TOKEN = 86400000;
	@Autowired
	private ActorService actorService;

	@Autowired
	private TrabajadorService trabajadorService;

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PatrocinadorService patrocinadorService;

	public static String getToken(HttpServletRequest request) {
		String tokenBearer = request.getHeader("Authorization");
		if (StringUtils.hasText(tokenBearer) && tokenBearer.startsWith("Bearer ")) {
			return tokenBearer.substring(7);
		}
		return null;
	}

	public static boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_FIRMA).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT ha experido o no es valido");
		}
	}

	public static String getUsernameOfToken(String token) {
		return Jwts.parser().setSigningKey(JWT_FIRMA).parseClaimsJws(token).getBody().getSubject();
	}

	public static String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + EXTENCION_TOKEN);

		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(fechaActual)
				.setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, JWT_FIRMA)
				.compact();
		return token;
	}

	public <T> T userLogin() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		T res = null;

		if (StringUtils.hasText(username)) {
			Optional<Actor> actorO = actorService.findByUsername(username);
			if (actorO.isPresent()) {
				Actor actor = actorO.get();
				switch (actor.getRol()) {
				case CLIENTE:
					Optional<Cliente> clienteOptional = clienteService.findByUsername(username);
					if (clienteOptional.isPresent()) {
						res = (T) clienteOptional.get();
					}
					break;
				case ADMINISTRADOR:
					Optional<Administrador> adminOptional = administradorService.findByUsername(username);
					if (adminOptional.isPresent()) {
						res = (T) adminOptional.get();
					}
					break;
				case TRABAJADOR:
					Optional<Trabajador> ayuntamientoOptional = trabajadorService.findByUsername(username);
					if (ayuntamientoOptional.isPresent()) {
						res = (T) ayuntamientoOptional.get();
					}
					break;

				case PATROCINADOR:
					Optional<Patrocinador> socioOptional = patrocinadorService.findByUsername(username);
					if (socioOptional.isPresent()) {
						res = (T) socioOptional.get();
					}
					break;
				}
			}
		}
		return res;
	}
}
