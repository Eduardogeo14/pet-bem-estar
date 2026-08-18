package br.edu.ifrs.petbemestar.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String telefone;
	private String cpf;
	private String email;
	
	private List<Pet> pets = new ArrayList<>();
	
	
	public Cliente() {}
	
	
	public Cliente(String nome, String cpf, String telefone, String email) {
		setNome(nome);
		setCpf(cpf);
		setTelefone(telefone);
		setEmail(email);
	}
	
	public void adicionarPet(Pet pet) {
		this.pets.add(pet);
		pet.setCliente(this);
	}

	public void setNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		} else {
			this.nome = nome;
		}
	}
	
	public void setCpf(String cpf) {
		if(cpf != null && cpf.matches("\\d{11}")) {
			this.cpf = cpf;
		} else {
			throw new IllegalArgumentException("CPF inválido");
		}
	}
	
	public void setTelefone(String telefone) {
		if(telefone != null && telefone.matches("\\d{10,11}")) {
			this.telefone = telefone;
		} else {
			throw new IllegalArgumentException("Telefone inválido");
		}
	}
	
	public void setEmail(String email) {
		if(email != null && email.contains("@") && email.contains(".com")) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Email inválido");
		}
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public String getCpf() {
		return this.cpf;
	}
	
	
	public List<Pet> getPets() {
		return this.pets;
	}
}