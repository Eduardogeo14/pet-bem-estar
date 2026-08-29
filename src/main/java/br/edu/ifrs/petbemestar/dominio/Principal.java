package br.edu.ifrs.petbemestar.dominio;



import br.edu.ifrs.petbemestar.dominio.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;

public class Principal {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet-bem-estar");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            
            Tutor tutor = new Tutor("Maria Silva", "12345678901", "51987654321", "maria@email.com");
            em.persist(tutor);

            
            Pet pet = new Pet(
                TipoBichoEnum.CACHORRO,
                "Rex",
                "Labrador",
                3,
                25.5,
                PorteEnum.GRANDE,
                "Muito brincalhão"
            );
            tutor.adicionarPet(pet);
            em.persist(pet);

       
            Atendimento atendimento = new Atendimento(
                LocalDateTime.now(),
                TipoServicoEnum.BANHO_E_TOSA
            );
            atendimento.setPet(pet);
            atendimento.setValor(120.0);
            pet.adicionarAtendimento(atendimento);
            em.persist(atendimento);

            em.getTransaction().commit();

            System.out.println("✅ Dados persistidos com sucesso!");
            System.out.println("Tutor: " + tutor.getNome());
            System.out.println("Pet: " + pet.getNomeBicho());
            System.out.println("Atendimento: " + atendimento.getTipo());

        } catch (Exception e) {
            // Só faz rollback se a transação ainda estiver ativa
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}