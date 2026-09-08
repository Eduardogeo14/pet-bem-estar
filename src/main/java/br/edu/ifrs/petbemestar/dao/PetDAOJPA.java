package br.edu.ifrs.petbemestar.dao;

import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import java.util.List;

import br.edu.ifrs.petbemestar.dominio.Pet;

public class PetDAOJPA implements PetDAO{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet-bem-estar");

	public void salvar(Pet pet) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(pet);
		em.getTransaction().commit();
		em.close();
	}
	
	public Pet buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Pet pet = em.find(Pet.class, id);
		em.close();
		return pet;
	}
	
	public List<Pet> listarTodos(){
		EntityManager em = emf.createEntityManager();
		List<Pet> pets = em.createQuery("FROM Pet", Pet.class).getResultList();
		em.close();
		return pets;
	}
	
	public void atualizar(Pet pet) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(pet);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remover(Long id) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Pet pet = em.find(Pet.class, id);
		
		if(pet != null) {
			em.remove(pet);
		}
		em.getTransaction().commit();
		em.close();
	}
}
