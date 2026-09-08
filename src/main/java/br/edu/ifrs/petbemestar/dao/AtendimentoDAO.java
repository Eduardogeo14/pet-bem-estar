package br.edu.ifrs.petbemestar.dao;

import br.edu.ifrs.petbemestar.dominio.*;
import java.util.List;

public interface AtendimentoDAO {
	
	void salvar(Atendimento atendimento);
	Atendimento buscarPorId(Long id);
	List<Atendimento> listarTodos();
	void atualizar(Atendimento atendimento);
	void remover(Long id);
	List<Atendimento> listarPorAnimal(Long idAnimal);
}
