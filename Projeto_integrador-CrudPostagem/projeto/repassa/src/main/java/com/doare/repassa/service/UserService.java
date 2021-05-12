package com.doare.repassa.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doare.repassa.model.User;
import com.doare.repassa.model.UserLogin;
import com.doare.repassa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User CadastrarUsuario(User usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> email){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> usuario = repository.findByEmail(email.get().getEmail());
		
		if (usuario.isPresent()) {
			if(encoder.matches(email.get().getSenha(), usuario.get().getSenha())) {
			
				String auth = email.get().getNome() + ":" + email.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				email.get().setToken(authHeader);
				email.get().setNome(usuario.get().getNome());
				email.get().setSenha(usuario.get().getSenha());
				
				return email;
			}
		}
		
		return null;
}
}
