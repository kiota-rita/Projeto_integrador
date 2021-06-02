package com.doare.repassa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="tb_tema")//indica que esta entidade é uma tabela de nome tb_tema
public class Tema {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id; //id da tabela tema
		
		@NotNull
		private String descricao; //descricao é referente aos temas de postagem
		
		
		@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("tema")
		private List<Postagem> postagem;
		
		//get and set
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
}
