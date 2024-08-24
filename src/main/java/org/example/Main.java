package org.example;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;

public class Main {
    public static void main(String[] args) {
            ClientCrudService clientService = new ClientCrudService();
            PlanetCrudService planetService = new PlanetCrudService();
            TicketCrudService ticketService = new TicketCrudService();

            try {

                Ticket ticketWithNullClient = new Ticket();
                ticketWithNullClient.setFromPlanet(new Planet());
                ticketWithNullClient.setToPlanet(new Planet());
                ticketService.createTicket(ticketWithNullClient);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }

            try {

                Client nonExistingClient = new Client();
                nonExistingClient.setId(999L);
                Ticket ticketWithNonExistingClient = new Ticket();
                ticketWithNonExistingClient.setClient(nonExistingClient);
                ticketWithNonExistingClient.setFromPlanet(new Planet());
                ticketWithNonExistingClient.setToPlanet(new Planet());
                ticketService.createTicket(ticketWithNonExistingClient);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }

            try {

                Ticket ticketWithNullPlanet = new Ticket();
                ticketWithNullPlanet.setClient(new Client());
                ticketService.createTicket(ticketWithNullPlanet);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }

            try {

                Planet nonExistingPlanet = new Planet();
                nonExistingPlanet.setId("X999");
                Ticket ticketWithNonExistingPlanet = new Ticket();
                ticketWithNonExistingPlanet.setClient(new Client());
                ticketWithNonExistingPlanet.setFromPlanet(nonExistingPlanet);
                ticketWithNonExistingPlanet.setToPlanet(nonExistingPlanet);
                ticketService.createTicket(ticketWithNonExistingPlanet);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            }

            clientService.close();
            planetService.close();
            ticketService.close();
        }
}
