import Cinemas.*;
import Excessoes.*;
import Recursos.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws nomeDuplicadoException, salaCheiaException, conflitoHorarioException {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        Cinema cinema1 = Cineart.getCineart();
        Cinema cinema2 = Cinemark.getCinemark();
        Cinema cinema3 = CineMax.getCineMax();
        Cinema cinema4 = Cinepolis.getCinepolis();

        do {
            System.out.println("\n=== Sistema de Gestao de Cinemas ===");
            System.out.println("1. Adicionar Sala a um Cinema");
            System.out.println("2. Criar Sessao em uma Sala");
            System.out.println("3. Criar Sessao apos a anterior");
            System.out.println("4. Criar Sessoes em toda a semana");
            System.out.println("5. Comprar ingressos");
            System.out.println("6. Listar Cinemas");
            System.out.println("7. Listar Salas de um Cinema");
            System.out.println("8. Listar Sessoes de uma Sala");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarSala(scanner);
                case 2 -> criarSessao(scanner);
                case 3 -> criarSessao(scanner, 1);
                case 4 -> criarSessao(scanner, 'a');
                case 5 -> novoIngresso(scanner);
                case 6 -> Cinema.listarCinemas();
                case 7 -> listarSalasDeCinema(scanner);
                case 8 -> listarSessoesDeSala(scanner);
                case 9 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 9);
    }

    private static void adicionarSala(Scanner scanner) throws nomeDuplicadoException {
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }
        try {
            cinema.criarSala();
        } catch (nomeDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void criarSessao(Scanner scanner)  throws conflitoHorarioException  {
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }

        cinema.listarSalas();
        System.out.print("Digite o nome da sala para criar a sessão: ");
        String nomeSala = scanner.nextLine();

        Sala sala = buscarSalaPorNome(cinema, nomeSala);
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }
        try {
            sala.criarSessao();
        } catch(conflitoHorarioException e){
            System.out.println(e.getMessage());
        }
    }

    private static void criarSessao(Scanner scanner, int id) throws conflitoHorarioException{
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }

        cinema.listarSalas();
        System.out.print("Digite o nome da sala para criar a sessão: ");
        String nomeSala = scanner.nextLine();

        Sala sala = buscarSalaPorNome(cinema, nomeSala);
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }

        try {
            sala.criarSessao(1);
        } catch(conflitoHorarioException e){
            System.out.println(e.getMessage());
        }
    }

    private static void criarSessao(Scanner scanner, char id) throws  conflitoHorarioException{
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }

        cinema.listarSalas();
        System.out.print("Digite o nome da sala para criar a sessão: ");
        String nomeSala = scanner.nextLine();

        Sala sala = buscarSalaPorNome(cinema, nomeSala);
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }

        try {
            sala.criarSessao('a');
        } catch(conflitoHorarioException e){
            System.out.println(e.getMessage());
        }

    }

    private static void novoIngresso(Scanner scanner) throws salaCheiaException {
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }

        cinema.listarSalas();
        System.out.print("Digite o nome da sala: ");
        String nomeSala = scanner.nextLine();

        Sala sala = buscarSalaPorNome(cinema, nomeSala);
        if (sala == null) {
            System.out.println("Sala nao encontrada.");
            return;
        }

        sala.listarSessoes();
        System.out.print("Digite o ID da sessao: ");
        int idSessao = scanner.nextInt();
        scanner.nextLine();

        Sessao sessao = buscarSessaoPorID(sala, idSessao);
        if (sessao == null) {
            System.out.println("Sessao nao encontrada.");
            return;
        }

        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite a quantidade de ingressos: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        try {
            sessao.compraIngressos(nome, qtd);
        } catch (salaCheiaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarSalasDeCinema(Scanner scanner) {
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }

        cinema.listarSalas();
    }

    private static void listarSessoesDeSala(Scanner scanner) {
        System.out.print("Digite o ID do cinema: ");
        int idCinema = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        Cinema cinema = buscarCinemaPorId(idCinema);
        if (cinema == null) {
            System.out.println("Cinema não encontrado.");
            return;
        }

        cinema.listarSalas();
        System.out.print("Digite o nome da sala: ");
        String nomeSala = scanner.nextLine();

        Sala sala = buscarSalaPorNome(cinema, nomeSala);
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }

        sala.listarSessoes();
    }

    private static Cinema buscarCinemaPorId(int id) {
        for (Cinema cinema : Cinema.getListaCinemas()) {
            if (cinema.getId() == id) {
                return cinema;
            }
        }
        return null;
    }

    private static Sala buscarSalaPorNome(Cinema cinema, String nomeSala) {
        for (Sala sala : cinema.getSalas()) {
            if (sala.getNome().equalsIgnoreCase(nomeSala)) {
                return sala;
            }
        }
        return null;
    }

    private static Sessao buscarSessaoPorID(Sala sala, int id) {
        for (Sessao sessao : sala.getSessoes()) {
            if (sessao.getId() == id){
                return sessao;
            }
        }
        return null;
    }
}
