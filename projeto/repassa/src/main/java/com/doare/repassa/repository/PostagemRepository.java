package com.doare.repassa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doare.repassa.model.Postagem;


@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	public List<Postagem>findAllByConteudoContainingIgnoreCase(String conteudo);
	

}
