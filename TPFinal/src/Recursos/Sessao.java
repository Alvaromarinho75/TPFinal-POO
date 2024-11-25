package Recursos;

import Excessoes.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sessao implements Serializable {
    private static final long serialVersionUID = 1L; 

    private static int contadorSessao = 1;

    private int id;
    private Sala sala;
    private Filme filme;
    private LocalDateTime dataHora;
    private ArrayList<Ingresso> ingressos;

    public Sessao(Sala sala, Filme filme, LocalDateTime dataHora) {
        this.id = contadorSessao++;
        this.sala = sala;
        this.filme = filme;
        this.dataHora = dataHora;
        this.ingressos = new ArrayList<>();
    }

    public LocalDateTime getHoraTermino() {
        long duracaoEmSegundos = filme.getDuracao();
        return dataHora.plusSeconds(duracaoEmSegundos);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String dataFormatada = dataHora.format(formatter);
        String dataTerminoFormatada = getHoraTermino().format(formatter);
        return "\nSessao" + '\n' +
                "ID: " + id + '\n' +
                "Filme: " + filme.getTitulo() + '\n' +
                "Sala: " + sala.getNome() + '\n' +
                "Ingressos Disponiveis: " + (sala.getCapacidade() - ingressos.size()) + '\n' +
                "Data/Hora de inicio:" + dataFormatada + '\n' +
                "Data/hora de termino:" + dataTerminoFormatada + '\n';
    }

    public void compraIngressos(String nome, int qtd) throws salaCheiaException {
        for (int i = 0; i < qtd; i++) {
            if(this.sala.ingressosRestantes(ingressos.size() + qtd) < 0) {
                throw new salaCheiaException("Capacidade insuficiente!");
            }
            Ingresso novoIngresso = new Ingresso(this.filme, 0, nome);
            this.ingressos.add(novoIngresso);
        }
    }

    public int getId() {return id;}
    public Sala getSala() {return sala;}
    public Filme getFilme() {return filme;}
    public LocalDateTime getDataHora() {return dataHora;}
    public ArrayList<Ingresso> getIngressos() {return ingressos;}
}
