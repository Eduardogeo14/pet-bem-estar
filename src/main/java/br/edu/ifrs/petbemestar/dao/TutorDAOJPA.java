package br.edu.ifrs.petbemestar.dao;


import jakarta.persistence.TypedQuery;

import br.edu.ifrs.petbemestar.dominio.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.List;

public class TutorDAOJPA implements TutorDAO{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet-bem-estar");
	
	public void salvar(Tutor tutor) {
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(tutor);
		em.getTransaction().commit();
		em.close();
	}
	
	public Tutor buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Tutor tutor = em.find(Tutor.class, id);
		em.close();
		return tutor;
	}
	
	public List<Tutor> listarTodos(){
		EntityManager em = emf.createEntityManager();
		List<Tutor> tutores = em.createQuery("FROM Tutor", Tutor.class).getResultList();
		em.close();
		return tutores;
	}
	
	public void atualizar(Tutor tutor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(tutor);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remover(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Tutor tutor = em.find(Tutor.class, id);
		
		if(tutor != null) {
			em.remove(tutor);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Tutor> listarPorNome(String nome){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Tutor> query = em.createQuery("SELECT t FROM Tutor t WHERE t.nome LIKE :nome", Tutor.class);
		query.setParameter("nome", "%" + nome + "%");
		List<Tutor> tutores = query.getResultList();
		em.close();
		return tutores;
	}
	
}
