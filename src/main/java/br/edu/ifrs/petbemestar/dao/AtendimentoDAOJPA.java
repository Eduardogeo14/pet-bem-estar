package br.edu.ifrs.petbemestar.dao;

import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

import br.edu.ifrs.petbemestar.dominio.Atendimento;

public class AtendimentoDAOJPA implements AtendimentoDAO{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet-bem-estar");
	
	public void salvar(Atendimento atendimento) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(atendimento);
		em.getTransaction().commit();
		em.close();
	}
	
	public Atendimento buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Atendimento atendimento = em.find(Atendimento.class, id);
		em.close();
		return atendimento;
	}
	
	public List<Atendimento> listarTodos(){
		EntityManager em = emf.createEntityManager();
		List<Atendimento> atendimentos = em.createQuery("SELECT a FROM Atendimento a", Atendimento.class).getResultList();
		em.close();
		return atendimentos;
	}
	
	public void atualizar(Atendimento atendimento) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(atendimento);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remover(Long id) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Atendimento atendimento = em.find(Atendimento.class, id);
		
		if(atendimento != null) {
			em.remove(atendimento);
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Atendimento> listarPorAnimal(Long idAnimal){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Atendimento> query = em.createQuery("SELECT a FROM Atendimento a WHERE a.pet.id = :idAnimal", Atendimento.class);
		query.setParameter("idAnimal", idAnimal);
		List<Atendimento> atendimentos = query.getResultList();
		em.close();
		return atendimentos;
	}
}
