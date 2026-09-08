package br.edu.ifrs.petbemestar.dao;

import br.edu.ifrs.petbemestar.dominio.*;
import java.util.List;

public interface PetDAO {
	
	void salvar(Pet pet);
	Pet buscarPorId(Long id);
	List<Pet> listarTodos();
	void atualizar(Pet pet);
	void remover(Long id);
	
}
