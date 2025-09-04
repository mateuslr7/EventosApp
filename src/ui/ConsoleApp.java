package ui;

import model.*;
import repository.EventRepository;
import service.EventService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EventRepository repo = new EventRepository();
        repo.load();
        EventService service = new EventService(repo);

        System.out.println("=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        User user = new User(nome, email, cidade);

        int opcao;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Cadastrar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Eventos acontecendo agora");
            System.out.println("4. Eventos passados");
            System.out.println("0. Sair");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarEvento(service);
                case 2 -> listarEventos(service);
                case 3 -> listar(service.getEventosAtuais());
                case 4 -> listar(service.getEventosPassados());
            }
        } while (opcao != 0);

        System.out.println("Encerrando...");
    }

    private static void cadastrarEvento(EventService service) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Categoria (FESTA, ESPORTE, SHOW, EDUCACAO, TECNOLOGIA, OUTROS): ");
        Categoria cat = Categoria.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Data e hora (yyyy-MM-dd HH:mm): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime data = LocalDateTime.parse(sc.nextLine(), formatter);
        
        Event e = new Event(nome, endereco, cat, data, desc);
        service.addEvent(e);
        System.out.println("Evento adicionado!");
    }

    private static void listarEventos(EventService service) {
        List<Event> eventos = service.getEventosOrdenados();
        listar(eventos);
    }

    private static void listar(List<Event> eventos) {
        for (Event e : eventos) {
            System.out.println(e);
            System.out.println("-----------------");
        }
    }
}
