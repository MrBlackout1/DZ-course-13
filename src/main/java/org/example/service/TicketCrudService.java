// TicketCrudService.java
package org.example.service;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import jakarta.persistence.*;
import java.util.List;

public class TicketCrudService {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("spacetravel");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Ticket createTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getFromPlanet() == null) {
            throw new IllegalArgumentException("Client and Planet cannot be null");
        }
        if (entityManager.find(Client.class, ticket.getClient().getId()) == null ||
                entityManager.find(Planet.class, ticket.getFromPlanet().getId()) == null) {
            throw new IllegalArgumentException("Client or Planet does not exist");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
        return ticket;
    }

    public Ticket getTicketById(Long id) {
        return entityManager.find(Ticket.class, id);
    }

    public List<Ticket> getAllTickets() {
        return entityManager.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
    }

    public Ticket updateTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getFromPlanet() == null) {
            throw new IllegalArgumentException("Client and Planet cannot be null");
        }
        if (entityManager.find(Client.class, ticket.getClient().getId()) == null ||
                entityManager.find(Planet.class, ticket.getFromPlanet().getId()) == null) {
            throw new IllegalArgumentException("Client or Planet does not exist");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
        return ticket;
    }

    public void deleteTicket(Long id) {
        entityManager.getTransaction().begin();
        Ticket ticket = entityManager.find(Ticket.class, id);
        if (ticket != null) {
            entityManager.remove(ticket);
        }
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
