package ManyWorker.controller;

import ManyWorker.entity.Actor;
import ManyWorker.service.ActorService;
import mrRebujito.entity.ActorLogin;
import mrRebujito.security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actores")
public class ActorController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody ActorLogin actorLogin) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(actorLogin.getUsername(), actorLogin.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JWTUtils.generateToken(authentication);

		return new ResponseEntity<String>(token, HttpStatus.OK);
		
	}
}
