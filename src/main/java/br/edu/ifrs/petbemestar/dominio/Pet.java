package br.edu.ifrs.petbemestar.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {
	
	@Id
	private Long id;
	
	private String nomeBicho;
	private String raca;
	private int idade;
	private String observacoes;
	private double peso;
	
	@Enumerated(EnumType.STRING)
	private TipoBichoEnum tipoBicho;
	
	
	private Cliente cliente;
	
	private List<Atendimento> atendimentos = new ArrayList<>();
	
	public Pet() {}
	
	public Pet(TipoBichoEnum tipoBicho, String nomeBicho, String raca, int idade, double peso, String observacoes) {
		setTipoBicho(tipoBicho);
		setNomeBicho(nomeBicho);
		this.raca = raca;
		setIdade(idade);
		setPeso(peso);
		this.observacoes = observacoes;
	}
	
	
	public void adicionarAtendimento(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
		atendimento.setPet(this);
	}

	public void setNomeBicho(String nomeBicho) {
		if(nomeBicho == null || nomeBicho.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		} else {
			this.nomeBicho = nomeBicho;
		}
	}
	
	public void setPeso(double peso) {
		if(peso <=0) {
			throw new IllegalArgumentException("Peso inválido");
		} else {
			this.peso = peso;
		}
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setTipoBicho(TipoBichoEnum tipoBicho) {
		if(tipoBicho == null) {
			throw new IllegalArgumentException("Tipo de bicho inválido");
		} else {
			this.tipoBicho = tipoBicho;
		}
	}
	
	public void setIdade(int idade) {
		if(idade < 0) {
			throw new IllegalArgumentException("Idade inválida");
		} else {
			this.idade = idade;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeBicho() {
		return this.nomeBicho;
	}
	
	public String getRaca() {
		return this.raca;
	}
	
	public TipoBichoEnum getTipoBicho() {
		return this.tipoBicho;
	}

	public int getIdade() {
		return this.idade;
	}
	
	public String getObservacoes() {
		return this.observacoes;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
}