package Recursos;

import java.io.Serializable;

public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private long duracao_s;
    private Sala sala;

    public Filme(String titulo, long duracao_s, Sala sala) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.duracao_s = duracao_s;
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "\nFilme" + '\n' +
                "ID: " + id + '\n' +
                "Titulo: " + titulo + '\n' +
                "Duracao: " + duracao_s + " segundos\n" +
                "Sala: " + (sala != null ? sala.getNome() : "Sem sala associada") + '\n';
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public long getDuracao() { return duracao_s; }
}
