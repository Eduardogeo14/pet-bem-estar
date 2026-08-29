package br.edu.ifrs.petbemestar.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoServicoEnum tipo;

    @Enumerated(EnumType.STRING)
    private StatusAgendamentoEnum situacao;

    private Double valor;

    @ManyToOne
    private Pet pet;

    public Atendimento() {}

    public Atendimento(LocalDateTime dataHora, TipoServicoEnum tipo) {
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.situacao = StatusAgendamentoEnum.MARCADO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoServicoEnum getTipo() {
        return tipo;
    }

    public StatusAgendamentoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(StatusAgendamentoEnum situacao) {
        this.situacao = situacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return tipo + " em " + dataHora + " (" + situacao + ")";
    }
}