package br.edu.ifrs.petbemestar.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeBicho;
	private String raca;
	private int idade;
	private String observacoes;
	private double peso;
	
	@Enumerated(EnumType.STRING)
	private TipoBichoEnum tipoBicho;

	@Enumerated(EnumType.STRING)
	private PorteEnum porte;
	
	@ManyToOne
	private Tutor cliente;
	
	@OneToMany(mappedBy = "pet")
	private List<Atendimento> atendimentos = new ArrayList<>();
	
	public Pet() {}
	
	public Pet(TipoBichoEnum tipoBicho, String nomeBicho, String raca, int idade, double peso, PorteEnum porte, String observacoes) {
		setTipoBicho(tipoBicho);
		setNomeBicho(nomeBicho);
		this.raca = raca;
		setIdade(idade);
		setPeso(peso);
		this.porte = porte;
		this.observacoes = observacoes;
	}
	
	public void adicionarAtendimento(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
		atendimento.setPet(this);
	}

	public Atendimento ultimoAtendimentoRealizado() {
		Atendimento ultimo = null;
		for (Atendimento atendimento : atendimentos) {
			if (atendimento.getSituacao() != StatusAgendamentoEnum.CONCLUIDO) {
				continue;
			}
			if (ultimo == null || atendimento.getDataHora().isAfter(ultimo.getDataHora())) {
				ultimo = atendimento;
			}
		}
		return ultimo;
	}

	public void setNomeBicho(String nomeBicho) {
		if(nomeBicho == null || nomeBicho.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		} else {
			this.nomeBicho = nomeBicho;
		}
	}
	
	public void setPeso(double peso) {
		if(peso <= 0) {
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

	public PorteEnum getPorte() {
		return this.porte;
	}

	public void setPorte(PorteEnum porte) {
		this.porte = porte;
	}

	public int getIdade() {
		return this.idade;
	}
	
	public String getObservacoes() {
		return this.observacoes;
	}
	
	public Tutor getCliente() {
		return cliente;
	}

	public void setCliente(Tutor cliente) {
		this.cliente = cliente;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	@Override
	public String toString() {
		return nomeBicho + " (" + tipoBicho + ")";
	}
}