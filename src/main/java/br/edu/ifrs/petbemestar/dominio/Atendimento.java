package br.edu.ifrs.petbemestar.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Atendimento {
	
    @Id
    private Long id;

    private LocalDateTime dataHora;
    private double valorCobrado;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private StatusAgendamentoEnum status;
    
    @Enumerated(EnumType.STRING)
    private TipoServicoEnum tipoServico;

    private Pet pet;
    private Servico servico;
    private Consulta consulta;
	
    public Atendimento() {}
	
    public Atendimento(LocalDateTime dataHora, TipoServicoEnum tipoServico, Pet pet, double valorCobrado) {
        setDataHora(dataHora);
        setTipoServico(tipoServico);
        setPet(pet);
        setValorCobrado(valorCobrado);
        this.status = StatusAgendamentoEnum.MARCADO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null || dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Horário e data inválidos.");
        }
        this.dataHora = dataHora;
    }

    public TipoServicoEnum getTipoServico() {
        return this.tipoServico;
    }

    public void setTipoServico(TipoServicoEnum tipoServico) {
        if (tipoServico == null) {
            throw new IllegalArgumentException("Tipo de serviço inválido.");
        }
        this.tipoServico = tipoServico;
    }

    public Pet getPet() {
        return this.pet;
    }

    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet inválido.");
        }
        this.pet = pet;
    }

    public double getValorCobrado() {
        return this.valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        if (valorCobrado < 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }
        this.valorCobrado = valorCobrado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusAgendamentoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamentoEnum status) {
        this.status = status;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}