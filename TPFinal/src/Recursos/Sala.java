package Recursos;

import Cinemas.*;
import Excessoes.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Sala {
    private static final long serialVersionUID =1L;

    private String nome;
    private int capacidade;
    private transient Cinema cinema;
    private ArrayList<Sessao> sessoes;

    public Sala(String nome, int capacidade, Cinema cinema) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.cinema = cinema;
        this.sessoes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "\nSala " + '\n' +
                "Nome: " + nome + '\n' +
                "Capacidade: " + capacidade + '\n' +
                "Cinema: " + (cinema != null ? cinema.getNome() : "Sem cinema") + '\n';
    }

    public void criarSessao() throws conflitoHorarioException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o titulo do filme: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a duracao do filme (em segundos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Filme novoFilme = new Filme(titulo, duracao, this);

        System.out.print("Digite a data e hora da sessão (formato: yyyy/MM/dd HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

        for(Sessao sessao : sessoes) {
            if(dataHora.isAfter(sessao.getDataHora()) && dataHora.isBefore(sessao.getHoraTermino())) {
                throw new conflitoHorarioException("Existe outra sessao nesse horario!");
            }
        }

        Sessao novaSessao = new Sessao(this, novoFilme, dataHora);
        sessoes.add(novaSessao);

        System.out.println("Sessao criada com sucesso!");
        System.out.println(novaSessao);
    }

    public void criarSessao(int id) throws conflitoHorarioException{
        Scanner scanner = new Scanner(System.in);

        LocalDateTime ultimoTermino = null;
        for (Sessao sessao : this.sessoes) {
            LocalDateTime termino = sessao.getDataHora().plusSeconds(sessao.getFilme().getDuracao());
            if (ultimoTermino == null || termino.isAfter(ultimoTermino)) {
                ultimoTermino = termino;
            }
        }

        if (ultimoTermino == null) {
            throw new conflitoHorarioException("Nao ha sessoes nessa sala!");
        }

        System.out.print("Digite o titulo do filme: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a duracao do filme (em segundos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Filme novoFilme = new Filme(titulo, duracao, this);

        LocalDateTime inicioNovaSessao = ultimoTermino.plusMinutes(30);

        Sessao novaSessao = new Sessao(this, novoFilme, inicioNovaSessao);
        sessoes.add(novaSessao);

        System.out.println("Sessao criada com sucesso!");
        System.out.println(novaSessao);
    }

    public void criarSessao(char id) throws conflitoHorarioException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o título do filme: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a duração do filme (em segundos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Filme novoFilme = new Filme(titulo, duracao, this);

        System.out.print("Digite a data e hora da sessão (formato: yyyy/MM/dd HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

        int diaDaSemana = dataHora.getDayOfWeek().getValue();
        LocalDateTime inicioDaSemana = dataHora.minusDays(diaDaSemana - 1);

        for (int i = 0; i < 7; i++) {
            LocalDateTime dataAtual = inicioDaSemana.plusDays(i);

            for (Sessao sessao : sessoes) {
                if (dataAtual.toLocalDate().equals(sessao.getDataHora().toLocalDate())
                        && dataAtual.isAfter(sessao.getDataHora())
                        && dataAtual.isBefore(sessao.getHoraTermino())) {
                    throw new conflitoHorarioException("Existe outra sessão nesse horário no dia " + dataAtual.toLocalDate());
                }
            }

            Sessao novaSessao = new Sessao(this, novoFilme, dataAtual);
            sessoes.add(novaSessao);
        }

        System.out.println("Sessoes criadas!");
        System.out.println(novoFilme);
    }

    public void listarSessoes() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessao cadastrada.");
        } else {
            System.out.println("Sessoes disponiveis na " + nome + ":");
            for (Sessao sessao : sessoes) {
                System.out.println(sessao);
            }
        }
    }

    public int ingressosRestantes(int qtd){
        return capacidade - qtd;
    }

    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }
    public String getNome() { return nome; }
    public int getCapacidade() { return capacidade; }
}
