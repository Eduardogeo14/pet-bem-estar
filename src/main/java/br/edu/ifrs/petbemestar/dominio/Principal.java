package br.edu.ifrs.petbemestar.dominio;


import br.edu.ifrs.petbemestar.dao.*;
import br.edu.ifrs.petbemestar.dominio.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


import java.time.LocalDateTime;

public class Principal {
    public static void main(String[] args) {
    	
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet-bem-estar");

        EntityManager em = emf.createEntityManager();
        
       em.getTransaction().begin();
       
       Tutor tutor = new Tutor("Maria Silva", "12345678901","51987654321","maria@email.com");
       em.persist(tutor);
       
       Pet pet1 = new Pet(TipoBichoEnum.CACHORRO, "Rex", "Labrador", 3, 25.5, PorteEnum.GRANDE, "Muito brincalhão");
       
    		tutor.adicionarPet(pet1);
    		em.persist(pet1);
    		
    		Atendimento atendimento1 = new Atendimento(LocalDateTime.now(), TipoServicoEnum.BANHO_E_TOSA);
    		
    		atendimento1.setPet(pet1);
    		atendimento1.setValor(120.0);
    		pet1.adicionarAtendimento(atendimento1);
    		em.persist(atendimento1);
    		
    		Atendimento atendimento2 = new Atendimento(LocalDateTime.now().plusDays(7), TipoServicoEnum.CONSULTA_VETERINARIA);
    		
    		atendimento2.setPet(pet1);
    		atendimento2.setValor(180.0);
    		em.persist(atendimento2);
    		
    		em.getTransaction().commit();
    		
    		System.out.println("Dados persistidos com sucesso \n");
    		
    		TutorDAO tutorDAO = new TutorDAOJPA();
    		
    		Tutor tutorParaAtualizar = tutorDAO.buscarPorId(tutor.getId());
    		tutorParaAtualizar.setEmail("maria.nova@email.com");
    		tutorDAO.atualizar(tutorParaAtualizar);
    		
    		System.out.println("Tutor atualizado com sucesso.");
    		
    		AtendimentoDAO atendimentoDAO = new AtendimentoDAOJPA();
    		atendimentoDAO.remover(atendimento2.getId());
    		
    		System.out.println("Atendimento removido com sucesso ");
    		
    		List<Atendimento> atendimentosRex = atendimentoDAO.listarPorAnimal(pet1.getId());
    		System.out.println("Atendimentos encontrados para o pet: " + atendimentosRex.size());
    		
    		for(Atendimento a: atendimentosRex) {
    			System.out.println("Atendimento: " + a.getTipo() + " Data/Hora: " + a.getDataHora());
    		}
    		
    		List<Atendimento> todosAtendimentos = atendimentoDAO.listarTodos();
    		System.out.println("Atendimentos encontrados: " + todosAtendimentos.size());
    		
    		for(Atendimento a: todosAtendimentos) {
    			System.out.println("Atendimento: " + a.getTipo() + " Data/Hora: " + a.getDataHora());
    		}
    		
    		em.close();
    		emf.close();
    		
}
    }