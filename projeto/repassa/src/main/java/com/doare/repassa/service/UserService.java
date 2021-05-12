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

	public User CadastrarUsuario(User email) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(email.getSenha());
		email.setSenha(senhaEncoder);

		return repository.save(email);
	}

	

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> usuario = repository.findByUsuario(user.get().getNome());

		if (user.isPresent()) {
			if (encoder.matches(user.get().getSenha(), user.get().getSenha())) {
			}
			String auth = user.get().getNome() + ":" + user.get().getSenha();
			byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodeAuth);
			user.get().setToken(authHeader);
			user.get().setNome(usuario.get().getNome());
			user.get().setSenha(usuario.get().getSenha());

			return user;
		}

		return null;
	}

	{
}
}
